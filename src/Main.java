import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        final TS3Config config = new TS3Config();
        config.setHost("104.214.227.48");
        //config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);

        final TS3Query query = new TS3Query(config);
        query.connect();

        final TS3Api api = query.getApi();
        api.login("serveradmin", args[0]);
        api.selectVirtualServerById(1);
        api.setNickname("ArmandBot");

        // Get our own client ID by running "whoami" command.
        final int clientId = api.whoAmI().getId();

        // Listen to chat in the channel the query is currently in
        // As we never changed the channel, this will be the default channel of the server
        api.registerEvent(TS3EventType.TEXT_CHANNEL, 0);
        api.registerEvent(TS3EventType.SERVER);
        api.registerEvent(TS3EventType.CHANNEL);

        // Register the event listener
        api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                // Only react to channel messages not sent by the query itself
                if(e.getTargetMode() == TextMessageTargetMode.CHANNEL && e.getInvokerId() != clientId){
                    String message = e.getMessage().toLowerCase();

                    if (message.equals("!ping")){
                        // Answer !ping with pong
                        api.sendChannelMessage("pong");
                    }
                    else if (message.equals("!help")){
                        // Send a command list
                        api.sendChannelMessage("You can use the following commands:\n" +
                                "!summon = Summons server admin (Kristure)\n" +
                                "!ping = Returns pong\n" +
                                "!channel = Creates a new private channel with you as its channel administrator");
                    }
                    else if (message.equals("!summon")){
                        // Send pushover notification
                        PushMessage pushover = new PushMessage();
                        pushover.push("You are summoned by " + e.getInvokerName());
                        api.sendChannelMessage(e.getInvokerName() + ": You have summoned the server admin. " +
                                "A notification has been sent to" +
                                " his phone.");
                    }
                    else if (message.startsWith("hello")){
                        // Greet whoever said hello
                        // Message: "Hello <client name>!"
                        api.sendChannelMessage("Hello " + e.getInvokerName() + "!");
                    }
                    else if (message.equals("!channel")){

                        final Map<ChannelProperty, String> properties = new HashMap<>();
                        properties.put(ChannelProperty.CHANNEL_FLAG_TEMPORARY, "1");
                        properties.put(ChannelProperty.CPID, "12"); // Make it a child of "private channels (CID: 12)
                        properties.put(ChannelProperty.CHANNEL_DELETE_DELAY, "1800"); //
                        properties.put(ChannelProperty.CHANNEL_CODEC_QUALITY, "7"); // Set codec quality to 7
                        if(api.getChannelByNameExact(e.getInvokerName(),true) == null){
                            api.createChannel(e.getInvokerName(), properties);
                            Channel newChan = api.getChannelByNameExact(e.getInvokerName(), false);
                            int userDbId = api.getDatabaseClientByUId(e.getInvokerUniqueId()).getDatabaseId();
                            api.moveQuery(1); // Move query back to default channel
                            api.setClientChannelGroup(5, newChan.getId(), userDbId);
                            api.sendChannelMessage(e.getInvokerName() + ": A channel in your name has been added." +
                                    " Right click your channel to change its properties.");
                        }
                        else{
                            api.sendChannelMessage(e.getInvokerName() + ": There already exist a channel with your " +
                                    "name. Right click it and change its name and before you try this command again.");
                        }

                    }
                }
            }
        });

        api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientJoin(ClientJoinEvent e) {
                if(api.getClientInfo(e.getClientId()).getDatabaseId() != 1){
                    api.sendPrivateMessage(e.getClientId(), "Welcome to the new Ouagadougou server. I am a bot" +
                            " created by the almighty Kristure. " +
                            "To use me, type [b]!help[/b] in the [b]Welcome[/b] channel.");

                    PushMessage pushover = new PushMessage();
                    pushover.push(e.getClientNickname() + " has joined the teamspeak server.");
                }
            }
        });

        api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientMoved(ClientMovedEvent e) {
                if(e.getClientId() == 113) {
                    PushMessage pushover = new PushMessage();
                    pushover.push(api.getClientInfo(e.getClientId()).getNickname() + " moved to " +
                            api.getChannelInfo(e.getTargetChannelId()).getName(), 1);
                }else if (api.getClientInfo(e.getClientId()).getDatabaseId() == 1){
                    // Client is serveradmin. Do nothing
                }
                else{
                    PushMessage pushover = new PushMessage();
                    pushover.push(api.getClientInfo(e.getClientId()).getNickname() + " moved to " +
                            api.getChannelInfo(e.getTargetChannelId()).getName());
                }
            }
        });

    }
}
