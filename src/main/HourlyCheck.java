package main;

import com.github.theholywaffle.teamspeak3.TS3Api;

public class HourlyCheck implements Runnable {
    private final TS3Api api;
    private final Config config;
    private ServerStatus serverStatus;
    private Achievements achievements;

    public HourlyCheck(Config config, TS3Api api) {
        this.api = api;
        this.config = config;

        this.serverStatus = new ServerStatus(this.config, this.api);
        this.achievements = new Achievements(config.pushoverApi, config.pushoverUserId, this.api);
    }

    @Override
    public void run() {
        this.serverStatus.run();
        this.achievements.thousandHours();
        this.achievements.hundredHours();
        this.achievements.oneHour();
    }
}
