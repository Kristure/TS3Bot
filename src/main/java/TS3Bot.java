import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Map;

public class TS3Bot {
    public static Bot bot;
    public static int clientId;
    public static ClientDb clientDb;

    public static void main(String[] args){
        // TODO:
        //  Setup scheduled executor service
        //  Setup functionality for Pushover


        bot = new Bot("config/test/config.json");

        // Init clientDb and return Map with Clients connected.
        clientDb = new ClientDb();
        Map<Integer, Client> clientMap = clientDb.getClientMap();

        // Get the client ID of the bot
        clientId = bot.api.whoAmI().getId();

        TS3Listener listener = new TS3Listener();
        listener.startChatListener();
        listener.startClientJoinListener();
        listener.startClientLeaveListener();

    }
}
