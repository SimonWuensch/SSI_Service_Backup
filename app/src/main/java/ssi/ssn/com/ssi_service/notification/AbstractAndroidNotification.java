package ssi.ssn.com.ssi_service.notification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import ssi.ssn.com.ssi_service.activity.MainActivity;

public class AbstractAndroidNotification {

    public void newInstanceWithIntent(Activity activity, Intent intent) {
    }

    public Intent createResultIntent(Context context, long projectID) {
        return new Intent(context, MainActivity.class);
    }
}