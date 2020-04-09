package main;

import com.github.theholywaffle.teamspeak3.TS3Api;

public class MinuteCheck implements Runnable {
    private final Config config;
    private final TS3Api api;

    public MinuteCheck(Config config, TS3Api api) {
        this.config = config;
        this.api = api;
    }

    @Override
    public void run() {
        // TODO Move IdleCheck to run every minute.
    }
}
