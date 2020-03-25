import com.github.theholywaffle.teamspeak3.TS3Api;

public class ClientList implements Runnable {
    private Config config;
    private TS3Api api;

    public ClientList(Config config, TS3Api api) {
        this.config = config;
        this.api = api;
    }

    private void sendMessage() {
        PushMessage pushMessage = new PushMessage(this.config.pushoverApi, this.config.pushoverUserId);
        ClientStatus clientStatus = new ClientStatus(api);

        String message = "Clients:\n";
        message = message.concat(clientStatus.get());
        pushMessage.push(message);
    }

    @Override
    public void run() {
        sendMessage();
    }
}
