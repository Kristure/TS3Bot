public class Config {
    public Boolean setEnableCommunicationsLogging;
    public Boolean floodRateUnlimited;
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
                  String pushoverApi,
                  String pushoverUserId) {
        this.setEnableCommunicationsLogging = setEnableCommunicationsLogging;
        this.floodRateUnlimited = floodRateUnlimited;
        this.queryUsername = queryUsername;
        this.queryPassword = queryPassword;
        this.server = server;
        this.queryPort = queryPort;
        this.pushoverApi = pushoverApi;
        this.pushoverUserId = pushoverUserId;
    }
}
