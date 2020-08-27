import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDb {
    private Map<Integer, Client> clientMap = new HashMap<>();
    private List<Client> clientList;

    public void update() {
        clientList = TS3Bot.bot.api.getClients();
        for (Client cli : clientList) {
            clientMap.put(cli.getId(), cli);
        }
        clientList.clear();
    }

    public Map<Integer, Client> getClientMap() {
        return clientMap;
    }

    public ClientDb() {
        update();
    }

}
