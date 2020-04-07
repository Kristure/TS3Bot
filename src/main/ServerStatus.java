package main;

import com.github.theholywaffle.teamspeak3.TS3Api;
import net.pushover.client.MessagePriority;

public class ServerStatus{
    private final Config config;
    private final TS3Api api;

    public ServerStatus(Config config, TS3Api api) {
        this.config = config;
        this.api = api;
    }

    private void sendMessage() {
        if(CountNormalUsers.Users(api) > 1){
            PushMessage pushMessage = new PushMessage(this.config.pushoverApi, this.config.pushoverUserId);
            ClientsConnected clientsConnected = new ClientsConnected(api);

            String message = "Clients:\n";
            message = message.concat(clientsConnected.get());
            pushMessage.push(message, MessagePriority.QUIET);
        }
    }

    public void run() {
        sendMessage();
    }
}
