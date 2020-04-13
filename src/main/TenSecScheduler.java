package main;

import com.github.theholywaffle.teamspeak3.TS3Api;

public class TenSecScheduler implements Runnable {
    private final Config config;
    private final TS3Api api;

    public TenSecScheduler(Config config, TS3Api api) {
        this.config = config;
        this.api = api;
    }

    @Override
    public void run() {
//        IdleCheck idleCheck = new IdleCheck(this.api, this.config);
//        idleCheck.run();
        IdleCheck2 idleCheck2 = new IdleCheck2(this.api, this.config);
        idleCheck2.run();
    }
}
