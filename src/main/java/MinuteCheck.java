import com.github.theholywaffle.teamspeak3.TS3Api;

public class MinuteCheck implements Runnable {
    private final TS3Api api;

    public MinuteCheck(TS3Api api) {
        this.api = api;
    }

    @Override
    public void run() {
        // TODO Move IdleCheck to run every minute.
    }
}
