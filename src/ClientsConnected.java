import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.List;

public class ClientsConnected {
    private String clients = "";

    public ClientsConnected(TS3Api api) {
        List<Client> clientList = api.getClients();

        for (Client cli: clientList){
            if(cli.getType() == 0){
                // Init input and output status
                String micStatus = "not muted";
                String outputStatus = "not muted";

                if(cli.isInputMuted())
                    micStatus = "muted";

                if(!api.getClientInfo(cli.getId()).isInputHardware())
                    micStatus = "dis";

                if(cli.isAway()) {
                    micStatus = "away";
                    outputStatus = "muted";
                }
                if(cli.isOutputMuted())
                    outputStatus = "muted";

                if(!api.getClientInfo(cli.getId()).isOutputHardware())
                    outputStatus = "dis";

                String timeConnected = new ConvertTime().convert(api.getClientInfo(cli.getId()).getTimeConnected());

                clients = clients.concat(cli.getNickname() + " (" + api.getChannelInfo(cli.getChannelId()).getName() +
                        ") (" + micStatus + " | " + outputStatus + ") (" + timeConnected + ")\n");
            }
        }
    }

    public String get(){
        return clients;
    }
}
