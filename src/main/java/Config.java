public class Config {
    public Boolean setEnableCommunicationsLogging;
    public boolean floodRateUnlimited;
    public String queryUsername;
    public String queryPassword;
    public String server;
    public int queryPort;
    public String pushoverApi;
    public String pushoverUserId;

    public Config(Boolean setEnableCommunicationsLogging,
                  String queryUsername,
                  Boolean floodRateUnlimited,
                  String queryPassword,
                  String server,
                  Integer queryPort,
                  String pushover_api,
                  String pushover_user_id) {
        this.setEnableCommunicationsLogging = setEnableCommunicationsLogging;
        this.floodRateUnlimited = floodRateUnlimited;
        this.queryUsername = queryUsername;
        this.queryPassword = queryPassword;
        this.server = server;
        this.queryPort = queryPort;
        this.pushoverApi = pushover_api;
        this.pushoverUserId = pushover_user_id;
    }
}