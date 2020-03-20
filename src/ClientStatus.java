import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.List;

public class ClientStatus  {
    private String clients = "";

    public ClientStatus(TS3Api api) {
        List<Client> clientList = api.getClients();

        for (Client cli: clientList){
            if(cli.getType() == 0){
                clients = clients.concat(cli.getNickname() + " (" + api.getChannelInfo(cli.getChannelId()).getName() +
                        ")\n");
            }

        }
    }

    public String get(){
        return clients;
    }
}
