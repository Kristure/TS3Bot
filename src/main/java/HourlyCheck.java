import com.github.theholywaffle.teamspeak3.TS3Api;

public class HourlyCheck implements Runnable {
    private final TS3Api api;
    private ServerStatus serverStatus;
    private Achievements achievements;

    public HourlyCheck(TS3Api api) {
        this.api = api;

        this.serverStatus = new ServerStatus(this.api);
        this.achievements = new Achievements(Config.pushoverApi, Config.pushoverUserId, this.api);
    }

    @Override
    public void run() {
        this.serverStatus.run();
        this.achievements.thousandHours();

    }
}
