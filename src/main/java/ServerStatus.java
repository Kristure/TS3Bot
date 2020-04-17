import com.github.theholywaffle.teamspeak3.TS3Api;
import net.pushover.client.MessagePriority;

public class ServerStatus{
    private final TS3Api api;

    public ServerStatus(TS3Api api) {
        this.api = api;
    }

    private void sendMessage() {
        if(CountNormalUsers.Users(api) > 1){
            PushMessage pushMessage = new PushMessage(Config.pushoverApi, Config.pushoverUserId);
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
