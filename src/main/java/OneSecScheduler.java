import com.github.theholywaffle.teamspeak3.TS3Api;

public class OneSecScheduler implements Runnable {
    private final Config config;
    private final TS3Api api;

    public OneSecScheduler(Config config, TS3Api api) {
        this.config = config;
        this.api = api;
    }

    @Override
    public void run() {
//        IdleCheck idleCheck = new IdleCheck(this.api, this.config);
//        idleCheck.run();
        IdleCheck idleCheck = new IdleCheck(this.api, this.config);
        idleCheck.run();
    }
}
