package ssi.ssn.com.ssi_service.fragment.list.notification;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ssi.ssn.com.ssi_service.R;
import ssi.ssn.com.ssi_service.activity.MainActivity;
import ssi.ssn.com.ssi_service.model.data.source.Project;
import ssi.ssn.com.ssi_service.model.helper.FormatHelper;
import ssi.ssn.com.ssi_service.model.network.response.notification.objects.ResponseNotification;


public class FragmentNotificationListViewHolder extends RecyclerView.ViewHolder {

    private static String TAG = FragmentNotificationListViewHolder.class.getSimpleName();

    private View vStatusColor;
    private TextView tvNumber;
    private TextView tvTime;
    private TextView tvDate;
    private TextView tvNodePath;
    private TextView tvText;

    private View cardView;

    private MainActivity activity;

    private Project project;

    public FragmentNotificationListViewHolder(MainActivity activity, Project project, View cardView) {
        super(cardView);
        this.cardView = cardView;
        this.activity = activity;
        this.project = project;

        vStatusColor = cardView.findViewById(R.id.fragment_notification_list_card_view_view_status_color);
        tvNumber = (TextView) cardView.findViewById(R.id.fragment_notification_list_card_view_text_view_number);
        tvTime = (TextView) cardView.findViewById(R.id.fragment_notification_list_card_view_text_view_time);
        tvDate = (TextView) cardView.findViewById(R.id.fragment_notification_list_card_view_text_view_date);
        tvNodePath = (TextView) cardView.findViewById(R.id.fragment_notification_list_card_view_text_view_node_path);
        tvText = (TextView) cardView.findViewById(R.id.fragment_notification_list_card_view_text_view_text);
    }

    protected void assignData(final FragmentNotificationList fragment, int position, final ResponseNotification notification) {
        vStatusColor.setBackgroundColor(notification.getDefinition().getSeverity().getColor(activity));
        tvNumber.setText((position + 1) + "");
        tvText.setText(notification.getText());

        String[] dateTime = FormatHelper.formatDate(notification.getStartTime()).split(" - ");
        tvDate.setText(dateTime[0]);
        tvTime.setText(dateTime[1]);

        String nodePath = notification.getNodePath();
        String node = nodePath == null ? "" : notification.getNodePath().substring(nodePath.lastIndexOf(".") + 1, nodePath.length());
        tvNodePath.setText(node);

        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (fragment.filter == null) {
                    activity.showCreateNotificationFilterFragment(project.get_id(), notification);
                }
            }
        });
    }
}
