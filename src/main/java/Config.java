public class Config {
    public static Boolean setEnableCommunicationsLogging;
    public static Boolean floodRateUnlimited;
    public static String queryUsername;
    public static String queryPassword;
    public static String server;
    public static Integer queryPort;
    public static String pushoverApi;
    public static String pushoverUserId;

    public Config(Boolean setEnableCommunicationsLogging,
                  Boolean floodRateUnlimited,
                  String queryUsername,
                  String queryPassword,
                  String server,
                  Integer queryPort,
                  String pushoverApi,
                  String pushoverUserId) {
        Config.setEnableCommunicationsLogging = setEnableCommunicationsLogging;
        Config.floodRateUnlimited = floodRateUnlimited;
        Config.queryUsername = queryUsername;
        Config.queryPassword = queryPassword;
        Config.server = server;
        Config.queryPort = queryPort;
        Config.pushoverApi = pushoverApi;
        Config.pushoverUserId = pushoverUserId;
    }
}