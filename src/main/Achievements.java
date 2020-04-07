package main;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.List;

public class Achievements {
    private final String pushoverApi;
    private final String pushoverUserId;
    private final TS3Api api;

    public Achievements(String pushoverApi, String pushoverUserId, TS3Api api) {
        this.pushoverApi = pushoverApi;
        this.pushoverUserId = pushoverUserId;
        this.api = api;
    }

    public void thousandHours() {
        List<Client> clientList = api.getClients();

        for (Client cli : clientList){
            long hoursConnected = ConvertTime.toHours(api.getClientInfo(cli.getId()).getTimeConnected());
            if(hoursConnected == 1000){
                api.sendServerMessage(String.format("%s has been connected for 1000 hours. Congratulations!",
                        cli.getNickname()));

                PushMessage pushMessage = new PushMessage(this.pushoverApi, this.pushoverUserId);
            }
        }
    }
}
