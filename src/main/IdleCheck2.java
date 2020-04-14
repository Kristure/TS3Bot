package main;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class IdleCheck2 {
    private final TS3Api api;
    private final Config config;
    private static Map<Integer, Long> oldIdleTime = new HashMap<>();
    private static List<Client> clientList;
    private static Boolean justConnected = true;
    public static long maxIdleSeconds = 50*5;
    public IdleCheck2(TS3Api api, Config config) {
        this.api = api;
        this.config = config;

        updateMap();
    }

    private void updateMap() {
        clientList = api.getClients();
        for (Client cli : clientList) {
            if(IdleCheck2.justConnected) oldIdleTime.put(cli.getId(), cli.getIdleTime());
        }
    }

    public static void update(TS3Api api){
        clientList.clear();
        clientList = api.getClients();
        for (Client cli : clientList) {
            oldIdleTime.put(cli.getId(), cli.getIdleTime());
        }
    }


    private void idleChange() {
//        long maxIdleSeconds = TimeUnit.MINUTES.toSeconds(5);

        ClientsConnected clientsConnected = new ClientsConnected(api);

        if (IdleCheck2.justConnected) {
            for (Client cli : clientList) {
                if (cli.getIdleTime() > TimeUnit.SECONDS.toMillis(maxIdleSeconds)) {
                    ClientIdle.idleMap.put(cli.getId(), true);
                } else {
                    ClientIdle.idleMap.put(cli.getId(), false);
                }
                System.out.println("No changes in idle yet");
            }
            IdleCheck2.justConnected = false;
        }


        for (Client cli : clientList) {
            if (cli.getType() != 0) continue;

            long idleTime = cli.getIdleTime();
            long maxIdleInMillis = TimeUnit.SECONDS.toMillis(maxIdleSeconds);
            Date time = Calendar.getInstance().getTime();

//            System.out.printf("%s %s", cli.getNickname(), ConvertTime.convert(cli.getIdleTime()));
//            System.out.println(" " + ClientIdle.idleMap.get(cli.getId()));


            if (idleTime > maxIdleInMillis && oldIdleTime.get(cli.getId()) < maxIdleInMillis) {
                PushMessage pushMessage = new PushMessage(config.pushoverApi, config.pushoverUserId);
                pushMessage.push(cli.getNickname() + " is now idle!" + "\n\n"
                        + clientsConnected.get());

                System.out.println(time + " - " + cli.getNickname() + " is idle");
                ClientIdle.idleMap.put(cli.getId(), true);
            }
            if (idleTime < maxIdleInMillis && oldIdleTime.get(cli.getId()) > maxIdleInMillis) {
                PushMessage pushMessage = new PushMessage(config.pushoverApi, config.pushoverUserId);
                pushMessage.push(
                        cli.getNickname() +
                                " is back from being idle for "
                                + ConvertTime.convert(oldIdleTime.get(cli.getId())) + "\n\n"
                                + clientsConnected.get());
                System.out.printf(time + " - " + "%s is back from being idle for %s\n", cli.getNickname(), ConvertTime.convert(oldIdleTime.get(cli.getId())));
                ClientIdle.idleMap.put(cli.getId(), false);
            }
            oldIdleTime.put(cli.getId(), cli.getIdleTime());
        }
    }

    public void run() {
        idleChange();
    }
}
