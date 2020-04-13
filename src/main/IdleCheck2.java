package main;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IdleCheck2 {
    private final TS3Api api;
    private final Config config;
    private Map<Integer, Client> clientMap = new HashMap<>();
    private List<Client> clientList;
    public static Boolean justConnected = true;

    public IdleCheck2(TS3Api api, Config config) {
        this.api = api;
        this.config = config;

        updateMap();
    }

    private void updateMap() {
        clientMap.clear();
        clientList = api.getClients();
        for(Client cli : clientList){
            clientMap.put(cli.getId(), cli);
        }
    }


    private void idleChange() {
        long maxIdleSeconds = 60 * 5;

        ClientsConnected clientsConnected = new ClientsConnected(api);

        if(justConnected){
            for (Client cli : clientList){
                if (cli.getIdleTime() > (long)TimeUnit.SECONDS.toMillis(maxIdleSeconds)) {
                    ClientIdle.idleMap.put(cli.getId(), true);
                } else {
                    ClientIdle.idleMap.put(cli.getId(), false);
                }
                System.out.println("No changes in idle yet");
            }
            justConnected = false;
        }


        for(Client cli : clientList) {
            if(cli.getType() != 0) continue;


            long oldIdleTime = clientMap.get(cli.getId()).getIdleTime();

            System.out.printf("%s %d", cli.getNickname(), cli.getIdleTime());
            System.out.println(" " + ClientIdle.idleMap.get(cli.getId()));
            if(cli.getIdleTime() > TimeUnit.SECONDS.toMillis(maxIdleSeconds) && !ClientIdle.idleMap.get(cli.getId())){
                PushMessage pushMessage = new PushMessage(config.pushoverApi, config.pushoverUserId);
                pushMessage.push(cli.getNickname() + " is now idle!" + "\n\n"
                        + clientsConnected.get());
                ClientIdle.idleMap.put(cli.getId(), true);
                System.out.println(cli.getNickname() + " is idle");
            }
            if(cli.getIdleTime() < TimeUnit.SECONDS.toMillis(maxIdleSeconds) && ClientIdle.idleMap.get(cli.getId())){
                PushMessage pushMessage = new PushMessage(config.pushoverApi, config.pushoverUserId);
                pushMessage.push(
                        cli.getNickname() +
                                " is back from being idle for "
                                + ConvertTime.convert(oldIdleTime) + "\n\n"
                                + clientsConnected.get());
                ClientIdle.idleMap.put(cli.getId(), false);
                System.out.printf("%s is back from being idle for %s \n", cli.getNickname(), ConvertTime.convert(oldIdleTime));
            }
        }
        updateMap();
    }

    public void run() {
        idleChange();
    }
}
