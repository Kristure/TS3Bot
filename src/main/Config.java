package main;

public class Config {
    public Boolean testing;
    public String queryPassword;
    public String server;
    public int queryPort;
    public String pushoverApi;
    public String pushoverUserId;
    public boolean floodRateUnlimited;

    public Config(Boolean testing, Boolean floodRateUnlimited, String queryPassword, String server, Integer queryPort, String pushover_api, String pushover_user_id) {
        this.testing = testing;
        this.floodRateUnlimited = floodRateUnlimited;
        this.queryPassword = queryPassword;
        this.server = server;
        this.queryPort = queryPort;
        this.pushoverApi = pushover_api;
        this.pushoverUserId = pushover_user_id;
    }
}