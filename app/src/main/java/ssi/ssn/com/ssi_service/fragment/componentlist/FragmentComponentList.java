package ssi.ssn.com.ssi_service.fragment.componentlist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.owlike.genson.GenericType;

import java.util.ArrayList;
import java.util.List;

import ssi.ssn.com.ssi_service.R;
import ssi.ssn.com.ssi_service.model.data.source.Project;
import ssi.ssn.com.ssi_service.model.helper.JsonHelper;
import ssi.ssn.com.ssi_service.model.helper.SourceHelper;
import ssi.ssn.com.ssi_service.model.network.response.component.ResponseComponent;

public class FragmentComponentList extends Fragment {

    public static String TAG = FragmentComponentList.class.getSimpleName();

    private static int FRAGMENT_LAYOUT = R.layout.fragment_component_list;
    private static int RECYCLERVIEW = R.id.fragment_component_list_recycler_view;
    private static int CARDVIEW = R.layout.fragment_component_list_card_view;

    private static String PROJECT_JSON = TAG + "PROJECT_JSON";
    private static String RESPONSE = TAG + "RESPONSE";
    private static String RESPONSE_COMPONENT_LIST = TAG + "RESPONSE_COMPONENT_LIST";

    private View rootView;
    private Project project;
    private List<ResponseComponent> responseComponentList;

    public static FragmentComponentList newInstance(Project project, List<ResponseComponent> responseComponentList) {
        if (project == null) {
            return new FragmentComponentList();
        }

        FragmentComponentList fragment = new FragmentComponentList();
        Bundle bundle = new Bundle();
        bundle.putString(PROJECT_JSON, JsonHelper.toJson(project));
        bundle.putString(RESPONSE, project.getDefaultResponseApplicationConfig().getResult());
        bundle.putSerializable(RESPONSE_COMPONENT_LIST, JsonHelper.toJson(responseComponentList));
        fragment.setArguments(bundle);
        return fragment;

    }

    private void loadArguments() {
        if (getArguments() == null) {
            return;
        }
        String projectJson = getArguments().getString(PROJECT_JSON);
        project = (Project) JsonHelper.fromJsonGeneric(Project.class, projectJson);
        String jsonResponseComponentList = getArguments().getString(RESPONSE_COMPONENT_LIST);
        responseComponentList = (ArrayList<ResponseComponent>) JsonHelper.fromJsonGeneric(new GenericType<List<ResponseComponent>>() {
        }, jsonResponseComponentList);
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

            RecyclerView.Adapter mAdapter = new FragmentComponentListAdapter(CARDVIEW, this, responseComponentList);
            Log.d(TAG, "Adapter [" + mAdapter.getClass().getSimpleName() + "] with CardView [" + getActivity().getResources().getResourceName(CARDVIEW) + "] initialized.");

            RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(RECYCLERVIEW);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(mAdapter);
            Log.d(TAG, "RecyclerView [" + getActivity().getResources().getResourceName(RECYCLERVIEW) + "] initialized.");

            initViewComponents();
        }
        return rootView;
    }

    public void initViewComponents() {
        TextView tvHeadLine = (TextView) rootView.findViewById(R.id.default_action_bar_text_view_headline);
        tvHeadLine.setText(SourceHelper.getString(getActivity(), R.string.fragment_component_list_title));
    }


}
