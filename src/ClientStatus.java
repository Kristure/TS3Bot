import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.List;

public class ClientStatus  {
    private String clients = "";

    public ClientStatus(TS3Api api) {
        List<Client> clientList = api.getClients();

        for (Client cli: clientList){
            if(cli.getType() == 0){
                // Init input and output status
                String micStatus = "not muted";
                String outputStatus = "not muted";

                if(cli.isInputMuted())
                    micStatus = "muted";
                else if(cli.isAway())
                    micStatus = "away";

                if(cli.isOutputMuted())
                    outputStatus = "muted";

                clients = clients.concat(cli.getNickname() + " (" + api.getChannelInfo(cli.getChannelId()).getName() +
                        ") (" + micStatus + " | " + outputStatus + ")\n");
            }a
        }
    }

    public String get(){
        return clients;
    }
}
