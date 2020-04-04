package main;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdleCheck implements Runnable {

    private List<Client> clientList;
    private Map<Integer, Client> clientMap = new HashMap<>();
    private TS3Api api;
    private Config config;

    public IdleCheck(TS3Api api, Config config) {
        this.api = api;
        this.config = config;

        updateMap();

    }

    private void updateMap(){
        clientMap.clear();
        clientList = api.getClients();
        for(Client cli : clientList){
            clientMap.put(cli.getId(), cli);
        }
    }

    private void idleChange () {
        clientList = api.getClients();

        ClientsConnected clientsConnected = new ClientsConnected(api);

        for(Client cli : clientList){
            if(cli.getType()!=0) continue;
            ConvertTime time = new ConvertTime();

            long oldTime = clientMap.get(cli.getId()).getIdleTime();
            long newTime = cli.getIdleTime();


            // Set maxIdleValue
            int maxIdleValue = 60000 * 5;
            if(clientMap.get(cli.getId()).getIdleTime() > maxIdleValue){
                if(oldTime - newTime > maxIdleValue){

                    PushMessage pushMessage = new PushMessage(config.pushoverApi, config.pushoverUserId);
                    pushMessage.push(
                            cli.getNickname() +
                                    " is back from being idle for "
                                    + time.convert(clientMap.get(cli.getId()).getIdleTime()) + "\n\n"
                                    + clientsConnected.get());
                }
            }else{
                if(cli.getIdleTime() > maxIdleValue){
                    PushMessage pushMessage = new PushMessage(config.pushoverApi, config.pushoverUserId);
                    pushMessage.push(cli.getNickname() + " is now idle!" + "\n\n"
                            + clientsConnected.get());
                }
            }
            updateMap();
        }

    }

    @Override
    public void run() {
        idleChange();
    }
}
