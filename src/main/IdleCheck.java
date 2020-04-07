package main;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

            long oldIdleTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(clientMap.get(cli.getId()).getIdleTime());
            long oldIdleTime = clientMap.get(cli.getId()).getIdleTime();
            long idleTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(cli.getIdleTime());
            long idleTime = cli.getIdleTime();


            // Set maxIdleValue
            long maxIdleSeconds = 60 * 5;
            if(idleTimeInSeconds >= maxIdleSeconds && idleTimeInSeconds < maxIdleSeconds + 5){
                PushMessage pushMessage = new PushMessage(config.pushoverApi, config.pushoverUserId);
                pushMessage.push(cli.getNickname() + " is now idle!" + "\n\n"
                        + clientsConnected.get());

            }
            if(idleTimeInSeconds < maxIdleSeconds && oldIdleTimeInSeconds >= maxIdleSeconds){
                PushMessage pushMessage = new PushMessage(config.pushoverApi, config.pushoverUserId);
                pushMessage.push(
                        cli.getNickname() +
                                " is back from being idle for "
                                + ConvertTime.convert(oldIdleTime) + "\n\n"
                                + clientsConnected.get());
            }
        }
        updateMap();
    }

    @Override
    public void run() {
        idleChange();
    }
}
