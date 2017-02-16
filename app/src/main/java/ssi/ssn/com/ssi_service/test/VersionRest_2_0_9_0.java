package ssi.ssn.com.ssi_service.test;

public class VersionRest_2_0_9_0 extends TestRestResponse {

    // Response geholt von Server http://172.26.26.16:8180/ - "project":{"name":"TZ","location":"Giebelstadt","orderNr":"2x0"

    private static String LOGIN = "{\"id\":2824521,\"key\":\"inLlrYu9c6q1mBySFOA2VTX_V8A-CQFjy95zP6SM\",\"status\":\"LOGGED_IN\",\"actor\":{\"key\":\"admin\"},\"rights\":[\"scada-write\",\"component-write\",\"notification-write\",\"security-read\",\"application-read\",\"smt-read\",\"security-write\",\"kpi-read\",\"notification-read\",\"kpi-write\",\"component-read\",\"scada-read\"],\"createdOn\":1483533985642,\"lastModifiedOn\":1483533985715,\"clientAddress\":\"10.245.0.94\",\"clientAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36\"}";
    private static String LOGOUT = "{\"id\":2824735,\"key\":\"h2tYOymJeFEqTivDWcvsYL_5sdpkaKl2EJYRZfkg\",\"status\":\"LOGGED_OUT\",\"createdOn\":1483534612067,\"lastModifiedOn\":1483535346013,\"clientAddress\":\"10.245.0.94\",\"clientAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36\"}";
    private static String APPLICATION = "{\"state\":{\"since\":1482487804669,\"status\":\"RUNNING\"},\"enabledModules\":[\"Scada\",\"Kpi.UserCharts\",\"Kpi.UserDashboards\"],\"project\":{\"name\":\"TZ\",\"location\":\"Giebelstadt\",\"orderNr\":\"2x0\"},\"build\":{\"version\":\"2.0.9.0\",\"number\":\"8439\",\"builtBy\":\"WU-LIGHTHOUSE$\",\"builtOn\":1482487531909},\"time\":{\"stamp\":1483533997191,\"offset\":3600000}}";
    private static String APPLICATION_CONFIG = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><runtime-config runtime-type=\"TEST\" version=\"2.0\" license-key=\"5061f7ca2c1e8bfa6a04da499974aecc30323127\"><product><platform-modules><application-module suppress-threshold=\"0\" stop-on-error=\"false\"/><security-module><sessions timeout=\"180\" floating-max-active=\"20\" validation-period=\"1800\" remove-after=\"172800\"/></security-module><notification-module remove-after=\"12\"/><kpi-module user-charts-enabled=\"true\" user-dashboards-enabled=\"true\" remove-after=\"12\"/><components-module><app-server service-name=\"LIGHTHOUSE\"><connection address=\"http://localhost:10090\"/><application-connection address=\"http://localhost:8180\"/></app-server><license-server manage=\"true\" start-timeout=\"5000\" stop-timeout=\"5000\" overwatch=\"60\"><connection address=\"http://localhost:7100\"/></license-server><opcua-server initialize-client=\"true\" manage=\"true\" start-timeout=\"15000\" stop-timeout=\"10000\" overwatch=\"60\"><connection address=\"opc.tcp://localhost:7110\"/></opcua-server><webmi-server enabled=\"false\" manage=\"false\" start-timeout=\"10000\" stop-timeout=\"10000\" overwatch=\"60\"><connection address=\"http://localhost:7120\"/></webmi-server><database><connection address=\"jdbc:sqlserver://localhost:1433;databaseName=LIGHTHOUSE\" user=\"lighthouse\" password=\"lighthouse\"/></database></components-module></platform-modules><plugin-modules><scada-module enabled=\"true\" auto-start=\"true\"/><smt-module enabled=\"false\" auto-start=\"false\"><smt-adapter address=\"http://localhost:8081/smt-adapter\" user=\"lh\" password=\"lh\"/></smt-module></plugin-modules></product><project name=\"TZ\" order-nr=\"2x0\" location=\"Giebelstadt\"><customer name=\"TZ\" address=\"Giebelstadt\"/></project></runtime-config>";
    private static String COMPONENT_LICENSE = "{\"name\":\"license\",\"config\":{\"home\":\"${RUNTIME_HOME}/components/license\",\"address\":\"http://localhost:7100\",\"manage\":true,\"startTimeout\":5000,\"stopTimeout\":5000,\"overwatch\":60},\"state\":{\"name\":\"License\",\"managed\":true,\"status\":\"ONLINE\",\"since\":1483534924779}}";
    //private static String COMPONENT_OPCUA = "{\"name\":\"OPCUA\",\"config\":{\"home\":\"${RUNTIME_HOME}/components/opcua\",\"address\":\"opc.tcp://localhost:7110\",\"manage\":true,\"startTimeout\":15000,\"stopTimeout\":10000,\"overwatch\":60},\"state\":{\"name\":\"OpcUa\",\"managed\":true,\"status\":\"ONLINE\",\"since\":1483534924779}}";
    private static String COMPONENT_OPCUA = "{\"name\":\"OPCUA\",\"config\":{\"home\":\"${RUNTIME_HOME}/components/opcua\",\"address\":\"opc.tcp://localhost:7110\",\"manage\":true,\"startTimeout\":15000,\"stopTimeout\":10000,\"overwatch\":60},\"state\":{\"name\":\"OpcUa\",\"managed\":true,\"status\":\"NOT_ONLINE\",\"since\":1483534924779}}";
    private static String COMPONENT_WEBMI = "{\"name\":\"webmi\",\"config\":{\"home\":\"${RUNTIME_HOME}/components/webmi\",\"address\":\"http://localhost:7120\",\"manage\":false,\"startTimeout\":10000,\"stopTimeout\":10000,\"overwatch\":60},\"state\":{\"name\":\"WebMi\",\"managed\":false,\"status\":\"UNKNOWN\",\"since\":1482487800032}}";
    private static String MODULE_APPLICATION_STATE = "{\"name\":\"Application\",\"status\":\"RUNNING\",\"since\":1482487804669}";
    private static String MODULE_SECURITY_STATE = "{\"name\":\"Security\",\"status\":\"RUNNING\",\"since\":1482487804568}";
    //private static String MODULE_COMPONENT_STATE = "{\"name\":\"Component\",\"status\":\"RUNNING\",\"since\":1482487804560}";
    private static String MODULE_COMPONENT_STATE = "{\"name\":\"Component\",\"status\":\"NOT_RUNNING\",\"since\":1482487804560}";
    private static String MODULE_NOTIFICATION_STATE = "{\"name\":\"Notification\",\"status\":\"RUNNING\",\"since\":1482487804601}";
    private static String MODULE_KPI_STATE = "{\"name\":\"Kpi\",\"status\":\"RUNNING\",\"since\":1482487804584}";
    private static String MODULE_SCADA_STATE = "{\"name\":\"Scada\",\"status\":\"RUNNING\",\"since\":1482487804668}";
    private static String MODULE_SMT_STATE = "{\"name\":\"SMT\",\"status\":\"RUNNING\",\"since\":1482487804668}";
    private static String NOTIFICATION = "{\"count\":17,\"data\":[{\"id\":2822618,\"startTime\":1483524998645,\"text\":\"C01WC1100 message window conveyer is empty\",\"nodePath\":\"PLANT.PHYSICAL.CS.C01.C0100.C0100XXX201\",\"tKey\":\"PLANT.NS.TRIGGERS.C01\",\"tIdx\":619,\"definition\":{\"key\":\"6eaa193a4f897e099be820fbf3a11ddae52c2d58\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3949}},{\"id\":2822617,\"startTime\":1483524980622,\"text\":\"External control not in automatic\",\"nodePath\":\"PLANT.PHYSICAL.CS.F01.F0106.F0106TSLC072\",\"tKey\":\"PLANT.NS.TRIGGERS.F01\",\"tIdx\":5290,\"definition\":{\"key\":\"1dcbfaa3fda307e8c9afaa38a9a75d8e226b2fc8\",\"type\":\"SCADA\",\"severity\":\"WARN\",\"docNr\":1867}},{\"id\":2822590,\"startTime\":1483524859489,\"text\":\"Warning Waiting on MFS-order\",\"nodePath\":\"PLANT.PHYSICAL.CS.F01.F0104.F0104CC032\",\"tKey\":\"PLANT.NS.TRIGGERS.F01\",\"tIdx\":2358,\"definition\":{\"key\":\"94f0ec3525c82c6537b8e510ff719d2e42fe2cec\",\"type\":\"SCADA\",\"severity\":\"WARN\",\"docNr\":1831}},{\"id\":2822589,\"startTime\":1483524857493,\"text\":\"TL20 +P01 message wait for order\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL20.TL2000.TL2000XXX011\",\"tKey\":\"PLANT.NS.TRIGGERS.TL20\",\"tIdx\":175,\"definition\":{\"key\":\"405f2c146930684fdbb41252269359f046511409\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3980}},{\"id\":2821696,\"startTime\":1483520788139,\"text\":\"Testmode activated (143.0)(51;2;2;0)\",\"nodePath\":\"PLANT.PHYSICAL.SRM.R42\",\"tKey\":\"PLANT.NS.TRIGGERS.R42\",\"tIdx\":1064,\"definition\":{\"key\":\"6a9efb99dede1fa94a20f2f6d0bc647d3cdd2dab\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3065}},{\"id\":2602372,\"startTime\":1482488100512,\"text\":\"Connection lost\",\"nodePath\":\"PLANT.PHYSICAL.Wrapper.W111\",\"definition\":{\"key\":\"a3247a110ded748649648afe98491faed5d547e5\",\"type\":\"SCADA\",\"severity\":\"WARN\"}},{\"id\":2602305,\"startTime\":1482487806705,\"text\":\"TL2013LSD003 maintenance interval expired\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL20.TL20Lift\",\"tKey\":\"PLANT.NS.TRIGGERS.TL20\",\"tIdx\":64,\"definition\":{\"key\":\"1ba4471acd4c78caa638b6f34a488e040dd9b60c\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3618}},{\"id\":2602307,\"startTime\":1482487806705,\"text\":\"TL2014 Node-ID 23 message one or some motors off\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL20.TL20Lift\",\"tKey\":\"PLANT.NS.TRIGGERS.TL20\",\"tIdx\":546,\"definition\":{\"key\":\"5ba33eb0fe20f09a749fc4b55a6069fc34a75e56\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3961}},{\"id\":2602308,\"startTime\":1482487806705,\"text\":\"TL2014 Node-ID 33 message one or some motors off\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL20.TL20Lift\",\"tKey\":\"PLANT.NS.TRIGGERS.TL20\",\"tIdx\":642,\"definition\":{\"key\":\"905a44eb7408190d964e62254988cc0e36c5822a\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3961}},{\"id\":2602309,\"startTime\":1482487806705,\"text\":\"TL2014 Node-ID 41 message one or some motors off\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL20.TL20Lift\",\"tKey\":\"PLANT.NS.TRIGGERS.TL20\",\"tIdx\":674,\"definition\":{\"key\":\"7a4d0fefb97d0390110afaa2ac4bebe4c101d00c\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3961}},{\"id\":2602310,\"startTime\":1482487806705,\"text\":\"TL2014 Node-ID 43 message one or some motors off\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL20.TL20Lift\",\"tKey\":\"PLANT.NS.TRIGGERS.TL20\",\"tIdx\":738,\"definition\":{\"key\":\"12d08a1f81b43b9672439a931d1186a441a82787\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3961}},{\"id\":2602311,\"startTime\":1482487806705,\"text\":\"TL2014 Node-ID 52 message one or some motors off\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL20.TL20Lift\",\"tKey\":\"PLANT.NS.TRIGGERS.TL20\",\"tIdx\":802,\"definition\":{\"key\":\"7bf48058bc31e4cc26e034afa18a18d8d4ddb3eb\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3961}},{\"id\":2602312,\"startTime\":1482487806705,\"text\":\"TL2014 Node-ID 63 message one or some motors off\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL20.TL20Lift\",\"tKey\":\"PLANT.NS.TRIGGERS.TL20\",\"tIdx\":930,\"definition\":{\"key\":\"e729610942f12fe9a8386a0ad09a7310972a7165\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3961}},{\"id\":2602303,\"startTime\":1482487806696,\"text\":\"TL0113LAM003 maintenance interval expired\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL01.TL01Lift\",\"tKey\":\"PLANT.NS.TRIGGERS.TL01\",\"tIdx\":64,\"definition\":{\"key\":\"c60ca3a6edeec5f9edc919550108bff0ff1a4aac\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3618}},{\"id\":2602304,\"startTime\":1482487806696,\"text\":\"TL0114 Node-ID 21 message one or some motors off\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL01.TL01Lift\",\"tKey\":\"PLANT.NS.TRIGGERS.TL01\",\"tIdx\":482,\"definition\":{\"key\":\"00cb229ef7f107a4b57617201bfa082526c1884b\",\"type\":\"SCADA\",\"severity\":\"INFO\",\"docNr\":3961}},{\"id\":2602300,\"startTime\":1482487806647,\"text\":\"Warning Waiting on MFS-order\",\"nodePath\":\"PLANT.PHYSICAL.CS.F01.F0105.F0105LRB047\",\"tKey\":\"PLANT.NS.TRIGGERS.F01\",\"tIdx\":3606,\"definition\":{\"key\":\"94f0ec3525c82c6537b8e510ff719d2e42fe2cec\",\"type\":\"SCADA\",\"severity\":\"WARN\",\"docNr\":1831}},{\"id\":2602298,\"startTime\":1482487804676,\"text\":\"APP-INFO: Application Started\",\"definition\":{\"key\":\"5e07799b997382e6a55da379bed44f07e9898d90\",\"type\":\"APPLICATION\",\"severity\":\"INFO\"}}]}";
    private static String OS = "{\"name\":\"Windows Server 2012 R2\",\"version\":\"6.3\",\"arch\":\"amd64\",\"processors\":4,\"avgSystemLoad\":-1.0,\"processCpuLoad\":0.0004208007886676659,\"processCpuLoadLast\":0.000455733278140391,\"systemCpuLoad\":0.06487968004977607,\"systemCpuLoadLast\":0.0118079273392101,\"diskFreeSpace\":5441,\"jvmImplSpecifics\":{\"jvmImplClassName\":\"sun.management.OperatingSystemImpl\",\"CommittedVirtualMemorySize\":\"2895667200\",\"CommittedVirtualMemorySize0\":\"2895667200\",\"FreePhysicalMemorySize\":\"20454989824\",\"FreeSwapSpaceSize\":\"20513234944\",\"ProcessCpuLoad\":\"4.208007886676659E-4\",\"ProcessCpuTime\":\"1614250000000\",\"SystemCpuLoad\":\"0.06487968004977607\",\"TotalPhysicalMemorySize\":\"34359267328\",\"TotalSwapSpaceSize\":\"39459540992\"},\"disk\":{\"usable\":5441,\"total\":81567,\"fillPercent\":\"93.33\"}}";
    private static String SCADA_SYSTEMS = "[{\"name\":\"C01\",\"group\":\"DEFAULT\",\"address\":\"192.168.66.28\",\"nodePath\":\"PLANT.PHYSICAL.CS.C01\",\"type\":\"S7\",\"integrationType\":\"S7\",\"connectionStatus\":\"CONNECTED\",\"connectionStatusTimestamp\":1483514253793,\"connectionActive\":false,\"status\":\"NOT_AVAILABLE\"},{\"name\":\"F01\",\"group\":\"DEFAULT\",\"address\":\"192.168.66.25\",\"nodePath\":\"PLANT.PHYSICAL.CS.F01\",\"type\":\"S7\",\"integrationType\":\"S7\",\"connectionStatus\":\"CONNECTED\",\"connectionStatusTimestamp\":1483180706122,\"connectionActive\":false,\"status\":\"NOT_AVAILABLE\"},{\"name\":\"R01\",\"group\":\"DEFAULT\",\"address\":\"192.168.66.21\",\"nodePath\":\"PLANT.PHYSICAL.SRM.R01\",\"type\":\"S7\",\"integrationType\":\"S7\",\"connectionStatus\":\"CONNECTED\",\"connectionStatusTimestamp\":1483180706122,\"connectionActive\":false,\"status\":\"NOT_AVAILABLE\"},{\"name\":\"R42\",\"group\":\"DEFAULT\",\"address\":\"192.168.66.31\",\"nodePath\":\"PLANT.PHYSICAL.SRM.R42\",\"type\":\"S7\",\"integrationType\":\"S7\",\"connectionStatus\":\"CONNECTED\",\"connectionStatusTimestamp\":1483180706122,\"connectionActive\":false,\"status\":\"NOT_AVAILABLE\"},{\"name\":\"STS11\",\"group\":\"DEFAULT\",\"address\":\"192.168.66.13\",\"nodePath\":\"PLANT.PHYSICAL.SRM.STS11\",\"type\":\"S7\",\"integrationType\":\"S7\",\"connectionStatus\":\"CONNECTED\",\"connectionStatusTimestamp\":1483180706122,\"connectionActive\":false,\"status\":\"NOT_AVAILABLE\"},{\"name\":\"STS12\",\"group\":\"DEFAULT\",\"address\":\"192.168.66.16\",\"nodePath\":\"PLANT.PHYSICAL.SRM.STS12\",\"type\":\"S7\",\"integrationType\":\"S7\",\"connectionStatus\":\"CONNECTED\",\"connectionStatusTimestamp\":1483180706122,\"connectionActive\":false,\"status\":\"NOT_AVAILABLE\"},{\"name\":\"STS21\",\"group\":\"DEFAULT\",\"address\":\"192.168.66.44\",\"nodePath\":\"PLANT.PHYSICAL.SRM.STS21\",\"type\":\"S7\",\"integrationType\":\"S7\",\"connectionStatus\":\"CONNECTED\",\"connectionStatusTimestamp\":1483180706122,\"connectionActive\":false,\"status\":\"NOT_AVAILABLE\"},{\"name\":\"TL01\",\"group\":\"DEFAULT\",\"address\":\"192.168.66.10\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL01\",\"type\":\"S7\",\"integrationType\":\"S7\",\"connectionStatus\":\"CONNECTED\",\"connectionStatusTimestamp\":1483180706123,\"connectionActive\":false,\"status\":\"NOT_AVAILABLE\"},{\"name\":\"TL20\",\"group\":\"DEFAULT\",\"address\":\"192.168.66.40\",\"nodePath\":\"PLANT.PHYSICAL.CS.TL20\",\"type\":\"S7\",\"integrationType\":\"S7\",\"connectionStatus\":\"CONNECTED\",\"connectionStatusTimestamp\":1483481116373,\"connectionActive\":false,\"status\":\"NOT_AVAILABLE\"},{\"name\":\"W111\",\"group\":\"DEFAULT\",\"address\":\"192.168.66.26\",\"nodePath\":\"PLANT.PHYSICAL.Wrapper.W111\",\"type\":\"S7\",\"integrationType\":\"S7\",\"connectionStatus\":\"CONNECTION_ERROR\",\"connectionStatusTimestamp\":1482487814677,\"connectionActive\":false,\"status\":\"NOT_AVAILABLE\"}]";
    private static String SCADA_SCANNERS = "[]";
    private static String SESSIONSCURRENT_LOGGED_IN = "{\"id\":2824735,\"key\":\"h2tYOymJeFEqTivDWcvsYL_5sdpkaKl2EJYRZfkg\",\"status\":\"LOGGED_IN\",\"actor\":{\"key\":\"admin\"},\"rights\":[\"scada-write\",\"component-write\",\"notification-write\",\"security-read\",\"application-read\",\"smt-read\",\"security-write\",\"kpi-read\",\"notification-read\",\"kpi-write\",\"component-read\",\"scada-read\"],\"createdOn\":1483534612067,\"lastModifiedOn\":1483534921349,\"clientAddress\":\"10.245.0.94\",\"clientAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36\"}";
    private static String SESSIONSCURRENT_LOGGED_OUT = "{\"id\":2824735,\"key\":\"h2tYOymJeFEqTivDWcvsYL_5sdpkaKl2EJYRZfkg\",\"status\":\"LOGGED_OUT\",\"createdOn\":1483534612067,\"lastModifiedOn\":1483535346013,\"clientAddress\":\"10.245.0.94\",\"clientAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36\"}";

    public VersionRest_2_0_9_0() {
        super("2.0.9.0");

        addResponse("/services/security/login/admin/admin", LOGIN);
        addResponse("/services/security/logout", LOGOUT);
        addResponse("/services/application", APPLICATION);
        addResponse("/services/application/config", APPLICATION_CONFIG);
        addResponse("/services/component/license", COMPONENT_LICENSE);
        addResponse("/services/component/opcua", COMPONENT_OPCUA);
        addResponse("/services/component/webmi", COMPONENT_WEBMI);
        addResponse("/services/application/state", MODULE_APPLICATION_STATE);
        addResponse("/services/security/state", MODULE_SECURITY_STATE);
        addResponse("/services/component/state", MODULE_COMPONENT_STATE);
        addResponse("/services/notification/state", MODULE_NOTIFICATION_STATE);
        addResponse("/services/kpi/state", MODULE_KPI_STATE);
        addResponse("/services/scada/state", MODULE_SCADA_STATE);
        addResponse("/services/smt/state", MODULE_SMT_STATE);
        addResponse("/services/notification/table?orderBy=$<<startTime>>%20DESC&condition=$<<active>>", NOTIFICATION);
        addResponse("/services/application/diagnostics/os", OS);
        addResponse("/services/scada/systems", SCADA_SYSTEMS);
        addResponse("/services/scada/scanners", SCADA_SCANNERS);
        addResponse("/services/security/sessions/current", SESSIONSCURRENT_LOGGED_IN);
    }
}
