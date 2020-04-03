package main;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class CountNormalUsers {
    public static int Users(TS3Api api){
        int normalClients = 0;
        for (Client cli : api.getClients()){
            if(cli.getType()==0)
                normalClients++;
        }
        return normalClients;

    }
}
