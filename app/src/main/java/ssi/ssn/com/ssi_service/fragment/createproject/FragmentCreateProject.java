package ssi.ssn.com.ssi_service.fragment.createproject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ssi.ssn.com.ssi_service.R;
import ssi.ssn.com.ssi_service.activity.MainActivity;
import ssi.ssn.com.ssi_service.fragment.AbstractFragment;
import ssi.ssn.com.ssi_service.fragment.customlist.FragmentCustomList;
import ssi.ssn.com.ssi_service.model.data.ressource.Project;
import ssi.ssn.com.ssi_service.model.handler.FormatHelper;
import ssi.ssn.com.ssi_service.model.handler.JsonHelper;
import ssi.ssn.com.ssi_service.model.network.handler.RequestHandler;

public class FragmentCreateProject extends AbstractFragment {

    private enum Status {
        ADD,
        UPDATE,
        DELETE;
    }

    public static String TAG = FragmentCreateProject.class.getSimpleName();

    private static int FRAGMENT_LAYOUT = R.layout.fragment_create_project;

    private static String PROJECT_JSON = TAG + "PROJECT_JSON";

    private Status fragmentStatus;

    private EditText etProjectAddress;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etObservationInterval;
    private Button bFinal;
    private Spinner spTimeInput;

    private View rootView;

    private Project project;

    public static FragmentCreateProject newInstance(Project project) {
        if (project == null) {
            return new FragmentCreateProject();
        }

        FragmentCreateProject fragment = new FragmentCreateProject();
        Bundle bundle = new Bundle();
        bundle.putString(PROJECT_JSON, JsonHelper.toJson(project));
        fragment.setArguments(bundle);
        return fragment;
    }

    private void loadArguments() {
        if (getArguments() == null) {
            fragmentStatus = Status.ADD;
            return;
        }

        fragmentStatus = Status.DELETE;
        String projectJson = getArguments().getString(PROJECT_JSON);
        project = (Project) JsonHelper.fromJsonGeneric(Project.class, projectJson);
        Log.d(TAG, "Fragment status [" + fragmentStatus + "].");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(FRAGMENT_LAYOUT, container, false);
            Log.d(TAG, "Fragment inflated [" + getActivity().getResources().getResourceName(FRAGMENT_LAYOUT) + "].");

            initViewComponents();
            if(!fragmentStatus.equals(Status.ADD)) {
                fillViewComponentsWithProjectInfo();
            }
        }
        return rootView;
    }

    public void initViewComponents() {
        TextView tvHeadLine = (TextView) rootView.findViewById(R.id.default_action_bar_text_view_headline);
        tvHeadLine.setText(getActivity().getString(R.string.fragment_create_project_title));

        etProjectAddress = (EditText) rootView.findViewById(R.id.fragment_create_project_edit_text_project_address);
        etUserName = (EditText) rootView.findViewById(R.id.fragment_create_project_edit_text_user_name);
        etPassword = (EditText) rootView.findViewById(R.id.fragment_create_project_edit_text_password);
        etObservationInterval = (EditText) rootView.findViewById(R.id.fragment_create_project_edit_text_update_interval);

        Button bShowApplicationInfo = (Button) rootView.findViewById(R.id.fragment_create_project_button_show_project_application_info);
        bShowApplicationInfo.setOnClickListener(onClickShowApplicationInfo());

        spTimeInput = (Spinner) rootView.findViewById(R.id.fragment_create_project_spinner_time_input);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.fragment_create_project_drop_down_box_time_input, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_drop_down_item);
        spTimeInput.setAdapter(adapter);

        bFinal = (Button) rootView.findViewById(R.id.fragment_create_project_button_final);
        bFinal.setOnClickListener(onFinalButtonClick());
        Log.d(TAG, "Fragment view components initialized.");
    }

    public AsyncTask afterEditTextChangeTask() {
        return new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                fragmentStatus = FragmentCreateProject.Status.UPDATE;
                bFinal.setText(getActivity().getString(R.string.fragment_create_project_button_update_project));
            }
        };
    }

    public void fillViewComponentsWithProjectInfo() {
        if (project != null) {
            etProjectAddress.setText(project.getServerAddress());
            etUserName.setText(project.getUserName());
            etPassword.setText(project.getPassword());

            long millis = project.getObservationInterval();
            String observationInterval;
            if (FormatHelper.formatMillisecondsToMinutes(millis) % 60 != 0) {
                observationInterval = String.valueOf(FormatHelper.formatMillisecondsToMinutes(millis));
                spTimeInput.setSelection(0);
            } else {
                observationInterval = String.valueOf(FormatHelper.formatMillisecondsToHours(millis));
                spTimeInput.setSelection(1);
            }
            etObservationInterval.setText(observationInterval);

            switch (fragmentStatus) {
                case ADD:
                    bFinal.setText(getActivity().getString(R.string.fragment_create_project_button_add_project));
                    break;
                case DELETE:
                    bFinal.setText(getActivity().getString(R.string.fragment_create_project_button_delete_project));
                    super.onTextChangeListener(etProjectAddress, afterEditTextChangeTask());
                    super.onTextChangeListener(etUserName, afterEditTextChangeTask());
                    super.onTextChangeListener(etPassword, afterEditTextChangeTask());
                    super.onTextChangeListener(etObservationInterval, afterEditTextChangeTask());
                    break;
            }
            Log.d(TAG, "Fragment view components filled with project [" + project + "].");
        }
    }

    public Project getProjectWithViewComponents() {
        if(project == null){
            project = new Project();
        }
        project.setServerAddress(etProjectAddress.getText().toString());
        project.setUserName(etUserName.getText().toString());
        project.setPassword(etPassword.getText().toString());
        project.setObservationInterval(getObservationInterval());
        return project;
    }

    private long getObservationInterval() {
        String selectedItem = spTimeInput.getSelectedItem().toString();
        String[] spinnerItems = getActivity().getResources().getStringArray(R.array.fragment_create_project_drop_down_box_time_input);

        if (selectedItem.equals(spinnerItems[0])) {
            return FormatHelper.formatMinutesToMilliseconds(etObservationInterval.getText().toString());
        }
        if (selectedItem.equals(spinnerItems[1])) {
            return FormatHelper.formatHoursToMilliseconds(etObservationInterval.getText().toString());
        }
        return 0;
    }

    // ** APPLICATION INFO BUTTON CLICK ********************************************************* //

    public View.OnClickListener onClickShowApplicationInfo() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RequestHandler requestHandler = ((MainActivity) getActivity()).getRequestHandler();
                final ExecutorService executor = Executors.newSingleThreadExecutor();
                final Project project = getProjectWithViewComponents();

                requestHandler.getRequestApplicationTask(project).executeOnExecutor(executor);
                new AsyncTask<Object, Void, Object>() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        if (project.getDefaultResponseApplication().getCode() == 200) {
                            ((MainActivity) getActivity()).showCustomListFragment(R.string.fragment_custom_list_application_info_title, FragmentCustomList.Type.APPLICATION, project.getDefaultResponseApplication().getResult());
                        } else {
                            Toast.makeText(getActivity(), getActivity().getString(R.string.fragment_create_project_message_server_address_not_correct), Toast.LENGTH_SHORT).show();
                        }
                    }
                }.executeOnExecutor(executor);
            }
        };
    }


    // ** FINAL BUTTON CLICK ******************************************************************** //
    public View.OnClickListener onFinalButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Project project = getProjectWithViewComponents();
                switch (fragmentStatus) {
                    case ADD:
                        onClickProjectAdd(project);
                        break;
                    case UPDATE:
                        onClickProjectUpdate(project);
                        break;
                    case DELETE:
                        onClickProjectDelete(project);
                        break;
                }
            }
        };
    }

    public void onClickProjectAdd(final Project project) {
        final RequestHandler requestHandler = ((MainActivity) getActivity()).getRequestHandler();
        final ExecutorService executor = Executors.newSingleThreadExecutor();

        requestHandler.getRequestApplicationTask(project).executeOnExecutor(executor);
        new AsyncTask<Object, Void, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if (project.getDefaultResponseApplication().getCode() == 200) {
                    requestHandler.getRequestLoginTask(project).executeOnExecutor(executor);
                    new AsyncTask<Object, Void, Object>() {
                        @Override
                        protected Object doInBackground(Object... objects) {
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            if (project.getDefaultResponseLogin().getCode() == 200) {
                                project.loadProjectInfoFromApplicationInfo();
                                getSQLiteHelper().addProject(project);
                                ((MainActivity) getActivity()).showProjectListFragment();
                            } else {
                                Toast.makeText(getActivity(), getActivity().getString(R.string.fragment_create_project_message_login_data_not_correct), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }.executeOnExecutor(executor);
                } else {
                    Toast.makeText(getActivity(), getActivity().getString(R.string.fragment_create_project_message_server_address_not_correct), Toast.LENGTH_SHORT).show();
                }
            }
        }.executeOnExecutor(executor);
    }

    public void onClickProjectUpdate(final Project project) {
        getSQLiteHelper().updateProject(project);
        ((MainActivity) getActivity()).showProjectListFragment();
    }

    public void onClickProjectDelete(final Project project) {
        getSQLiteHelper().deleteProject(project);
        ((MainActivity) getActivity()).showProjectListFragment();
    }
}
