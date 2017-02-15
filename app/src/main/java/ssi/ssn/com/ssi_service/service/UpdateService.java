package ssi.ssn.com.ssi_service.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.Executors;

import ssi.ssn.com.ssi_service.R;
import ssi.ssn.com.ssi_service.fragment.projectlist.FragmentProjectListNotification;
import ssi.ssn.com.ssi_service.model.data.source.Project;
import ssi.ssn.com.ssi_service.model.data.source.cardobject.AbstractCardObject;
import ssi.ssn.com.ssi_service.model.database.SQLiteDB;
import ssi.ssn.com.ssi_service.model.helper.FormatHelper;
import ssi.ssn.com.ssi_service.model.helper.JsonHelper;
import ssi.ssn.com.ssi_service.model.helper.ObservationHelper;
import ssi.ssn.com.ssi_service.model.helper.SourceHelper;
import ssi.ssn.com.ssi_service.model.network.handler.RequestHandler;
import ssi.ssn.com.ssi_service.notification.AndroidNotificationHelper;

public class UpdateService extends Service {

    private static String TAG = UpdateService.class.getSimpleName();

    public static String JSON_PROJECT_LIST = TAG + "JSON_PROJECT_LIST";

    private final IBinder mBinder = new LocalBinder();
    private int startID;

    private boolean isDelayRunning = true;
    private AndroidNotificationHelper androidNotificationHelper;

    protected SQLiteDB sqliteDB;
    protected RequestHandler requestHandler;

    private List<Project> projects = new ArrayList<>();

    public class LocalBinder extends Binder {
        UpdateService getService() {
            return UpdateService.this;
        }
    }

    @Override
    public void onCreate() {
        androidNotificationHelper = new AndroidNotificationHelper(getBaseContext());
        sqliteDB = new SQLiteDB(this);
        requestHandler = RequestHandler.initRequestHandler(Executors.newSingleThreadExecutor());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Services Started", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Received start id " + startId + ": " + intent);
        this.startID = startId;

        List<String> jsonProjects = intent.getStringArrayListExtra(JSON_PROJECT_LIST);
        for (String jsonProject : jsonProjects) {
            projects.add((Project) JsonHelper.fromJsonGeneric(Project.class, jsonProject));
        }

        Log.d(TAG, "Project list size is [" + projects.size() + "]");
        startDelay();
        return START_STICKY;
    }

    public void startDelay() {
        for (final Project project : projects) {
            if (!project.isProjectObservation()) {
                continue;
            }
            final Handler handler = new Handler();
            final long interval = project.getObservationInterval();
            long ageInMillis = new Date().getTime() - project.getLastObservationTime();
            long firstStartAt;
            if (ObservationHelper.isProjectOutOfDate(project)) {
                firstStartAt = interval;
                detectStatusAndNotify(project);
            } else {
                firstStartAt = interval - ageInMillis;
            }
            Log.i(TAG, "First Start in [" + FormatHelper.formatMillisecondsToMinutes(firstStartAt) + "] minutes. Interval is [" + FormatHelper.formatMillisecondsToMinutes(interval) + "]. Project: [" + project + "]");

            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (!isDelayRunning) {
                        return;
                    }
                    detectStatusAndNotify(project);
                    handler.postDelayed(this, interval);
                }
            };
            handler.postDelayed(timerTask, firstStartAt);
        }
        Log.i(TAG, "Delay started.");
    }

    private void detectStatusAndNotify(final Project project) {
        new AsyncTask<Object, Void, Object>() {
            @Override
            protected Object doInBackground(Object... objects) {
                project.detectProjectStatus(sqliteDB, requestHandler);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if (project.getStatus().equals(ssi.ssn.com.ssi_service.model.data.source.Status.OK)) {
                    return;
                }

                switch (project.getApplicationStatus()) {
                    case NOT_AVAILABLE:
                        androidNotificationHelper.throwNotification(
                                getBaseContext(),
                                project.get_id(),
                                new FragmentProjectListNotification().createResultIntent(getBaseContext(), project.get_id()),
                                project.getStatus().getColor(getBaseContext()),
                                R.drawable.icon_project,
                                SourceHelper.getString(getApplicationContext(), R.string.project_status) + " " + project.getApplicationStatus().name(),
                                SourceHelper.getString(getApplicationContext(), R.string.notification_project_is_not_available) + " \n" + project.designation(),
                                SourceHelper.getString(getApplicationContext(), R.string.project_status) + " " + project.getStatus());
                        return;
                    case ERROR:
                        androidNotificationHelper.throwNotification(
                                getBaseContext(),
                                project.get_id(),
                                new FragmentProjectListNotification().createResultIntent(getBaseContext(), project.get_id()),
                                project.getStatus().getColor(getBaseContext()),
                                R.drawable.icon_project,
                                SourceHelper.getString(getApplicationContext(), R.string.project_status) + " " + project.getApplicationStatus().name(),
                                SourceHelper.getString(getApplicationContext(), R.string.notification_project_application_status_is_not_running) + " \n" + project.designation(),
                                SourceHelper.getString(getApplicationContext(), R.string.project_status) + " " + project.getStatus());
                        return;
                }

                if (project.getStatus().equals(ssi.ssn.com.ssi_service.model.data.source.Status.ERROR)) {
                    for (AbstractCardObject cardObject : project.getAllCardObjects()) {
                        switch (cardObject.getStatus()) {
                            case NOT_AVAILABLE:
                                androidNotificationHelper.throwNotification(
                                        getBaseContext(),
                                        project.get_id(),
                                        cardObject.getNotificationClass().createResultIntent(getBaseContext(), project.get_id()),
                                        cardObject.getStatus().getColor(getBaseContext()),
                                        cardObject.getIcon(),
                                        SourceHelper.getString(getApplicationContext(), cardObject.getTitle()) + " " + SourceHelper.getString(getApplicationContext(), R.string.status) + ": " + cardObject.getStatus().name(),
                                        SourceHelper.getString(getApplicationContext(), cardObject.getTitle()) + " " + SourceHelper.getString(getApplicationContext(), R.string.notification_not_available) + " \n" + project.designation(),
                                        SourceHelper.getString(getApplicationContext(), R.string.project_status) + " " + project.getStatus());
                                break;
                            case ERROR:
                                List<String> messages = cardObject.getNotificationMessages(getBaseContext());
                                StringBuilder notificationMessageBuilder = new StringBuilder();
                                for (String message : messages) {
                                    notificationMessageBuilder.append(message).append("\n");
                                }
                                String notificationMessage = notificationMessageBuilder.toString();

                                androidNotificationHelper.throwNotification(
                                        getBaseContext(),
                                        project.get_id(),
                                        cardObject.getNotificationClass().createResultIntent(getBaseContext(), project.get_id()),
                                        cardObject.getStatus().getColor(getBaseContext()),
                                        cardObject.getIcon(),
                                        SourceHelper.getString(getApplicationContext(), cardObject.getTitle()) + " " + SourceHelper.getString(getApplicationContext(), R.string.status) + ": " + cardObject.getStatus().name(),
                                        notificationMessage,
                                        SourceHelper.getString(getApplicationContext(), cardObject.getTitle()) + " " + SourceHelper.getString(getApplicationContext(), R.string.notification_includes_errors) + " [" + messages.size() + "]");
                                break;
                        }
                    }
                }
            }
        }.execute();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Services Stopped", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Services Stopped. Start id: " + startID);
        androidNotificationHelper.cancelAllNotifications();
        stopDelay();
    }

    public void stopDelay() {
        isDelayRunning = false;
        Log.i(TAG, "Delay stopped.");
    }
}