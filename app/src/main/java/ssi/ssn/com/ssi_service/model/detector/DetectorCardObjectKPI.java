package ssi.ssn.com.ssi_service.model.detector;

import android.util.Log;

import ssi.ssn.com.ssi_service.model.data.source.Project;
import ssi.ssn.com.ssi_service.model.data.source.Status;
import ssi.ssn.com.ssi_service.model.data.source.cardobject.CardObjectKPI;
import ssi.ssn.com.ssi_service.model.data.source.filter.FilterKPI;
import ssi.ssn.com.ssi_service.model.database.SQLiteDB;
import ssi.ssn.com.ssi_service.model.helper.JsonHelper;
import ssi.ssn.com.ssi_service.model.network.handler.RequestHandler;
import ssi.ssn.com.ssi_service.model.network.response.kpi.definitions.ResponseKPIDefinitionList;

public class DetectorCardObjectKPI {

    private static String TAG = DetectorCardObjectKPI.class.getSimpleName();

    public static void loadKpiDefinitionsFromNetwork(RequestHandler requestHandler, Project project) {
        CardObjectKPI cardObject = project.getCardObjectKPI();
        Log.d(TAG + " Project ID: " + cardObject.get_id_project(), cardObject.getClass().getSimpleName() + " start load card object kpi definitions information from network...");
        requestHandler.sendRequestLogin(project);
        requestHandler.sendRequestKPIDefinitions(project);

        if (project.getDefaultResponseKPIDefinitionList().getCode() != 200) {
            cardObject.setDefinitions(null);
            return;
        }

        ResponseKPIDefinitionList definitions = (ResponseKPIDefinitionList) JsonHelper.fromJsonGeneric(ResponseKPIDefinitionList.class, project.getDefaultResponseKPIDefinitionList().getResult());
        cardObject.setDefinitions(definitions);
        Log.d(TAG + " Project ID: " + cardObject.get_id_project(), "Response kpi definition list size is [" + cardObject.getDefinitions().getDefinitions().size() + "], [" + cardObject.getDefinitions().getDefinitions() + "]");
    }

    public static void loadAllKpiMeasurementsByAllFilters(RequestHandler requestHandler, Project project) {
        CardObjectKPI cardObject = project.getCardObjectKPI();
        for (FilterKPI filter : cardObject.getKpiFilters()) {
            loadKpiMeasurementByFilter(requestHandler, project, filter);
        }
    }

    public static void loadKpiMeasurementByFilter(RequestHandler requestHandler, Project project, FilterKPI filter) {
        //TODO Card Object Kpi -> Load Kpi Measurement By Filter from Network
        /*CardObjectKPI cardObject = project.getCardObjectKPI();
        Log.d(TAG + " Project ID: " + cardObject.get_id_project(), cardObject.getClass().getSimpleName() + " start load card object kpi filter information from network...");
        requestHandler.sendRequestLogin(project);
        requestHandler.sendRequestNotification(project, filter);

        if (project.getDefaultResponseNotification().getCode() != 200) {
            filter.setNotificationTable(null);
            return;
        }

        ResponseNotificationTable notificationTable = (ResponseNotificationTable) JsonHelper.fromJsonGeneric(ResponseNotificationTable.class, project.getDefaultResponseNotification().getResult());
        filter.setNotificationTable(notificationTable);
        filter.checkResponseNotificationTable();
        Log.d(TAG + " Project ID: " + cardObject.get_id_project() + " Filter: " + filter.identity(), "Response notification table size is [" + filter.getNotificationTable().getCount() + "], [" + filter.getNotificationTable().getData() + "]");*/
    }

    public static void detectCardStatus(SQLiteDB sqLiteDB, CardObjectKPI cardObject) {
        Log.d(TAG + " Project ID: " + cardObject.get_id_project(), cardObject.getClass().getSimpleName() + " start detecting card object kpi status...");
        Status overAllState = Status.OK;
        if (cardObject.getDefinitions() == null) {
            overAllState = Status.NOT_AVAILABLE;
        } else if (cardObject.getKpiFilters().isEmpty()) {
            overAllState = Status.OK;
        } else {
            for (FilterKPI filter : cardObject.getKpiFilters()) {
                //TODO CARD Object Kpi -> Filter status check
                /*if (filter.getActiveTimeReachedNotificationTable().getCount() > 0) {
                    overAllState = Status.ERROR;
                    break;
                }*/
            }
        }
        cardObject.setStatus(overAllState);
        boolean isSuccessful = cardObject.getDBSQLiteCardObject(sqLiteDB).update(cardObject);
        if (!isSuccessful) {
            isSuccessful = cardObject.getDBSQLiteCardObject(sqLiteDB).update(cardObject);
            if (!isSuccessful) {
                throw new NullPointerException("Can not update " + cardObject.getClass().getSimpleName() + " [" + cardObject + "]");
            }
        }
        Log.i(TAG + " Project ID: " + cardObject.get_id_project(), cardObject.getClass().getSimpleName() + " status: " + cardObject.getStatus());

    }
}