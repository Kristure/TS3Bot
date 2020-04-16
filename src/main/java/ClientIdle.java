import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientIdle {
    private final int id;
    private final TS3Api api;
    private static List<Client> clientList;
    public static Map<Integer, Boolean> idleMap = new HashMap<>();

    public ClientIdle(int id, TS3Api api) {
        this.id = id;
        this.api = api;
    }

    public static void updateMap(TS3Api api){
        clientList = api.getClients();
        for (Client cli : clientList){
            ClientIdle.idleMap.put(cli.getId(), false);
        }
    }
}
