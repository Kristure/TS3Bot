import com.github.theholywaffle.teamspeak3.TS3Api;

public class ServerStatus implements Runnable {
    private Config config;
    private TS3Api api;

    public ServerStatus(Config config, TS3Api api) {
        this.config = config;
        this.api = api;
    }

    private void sendMessage() {
        if(api.getClients().size() > 1){
            PushMessage pushMessage = new PushMessage(this.config.pushoverApi, this.config.pushoverUserId);
            ClientsConnected clientsConnected = new ClientsConnected(api);

            String message = "Clients:\n";
            message = message.concat(clientsConnected.get());
            pushMessage.push(message);
        }
    }

    @Override
    public void run() {
        sendMessage();
    }
}
