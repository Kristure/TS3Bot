import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import net.pushover.client.MessagePriority;
import net.pushover.client.PushoverMessage;

import java.util.HashMap;
import java.util.Map;

public class TS3Listener {
    Boolean serverEvent = false;

    public void startChatListener() {
        TS3Bot.bot.api.registerEvent(TS3EventType.TEXT_SERVER);

        TS3Bot.bot.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                if (e.getTargetMode() == TextMessageTargetMode.SERVER && e.getInvokerId() != TS3Bot.clientId) {
                    String message = e.getMessage().toLowerCase();

                    if (message.equals("!ping")) {
                        TS3Bot.bot.api.sendServerMessage("pong");
                    } else if (message.equals("!help")) {
                        TS3Bot.bot.api.sendServerMessage("Commands:\n" +
                                "!summon = Summons server admin\n" +
                                "!ping = Returns pong\n" +
                                "!channel = Creates a new private channel with you as its channel administrator");
                    } else if (message.equals("!summon")) {
                        PushMessage pushMessage = new PushMessage();
                        pushMessage.push("You are summoned by " + e.getInvokerName());
                        TS3Bot.bot.api.sendServerMessage(e.getInvokerName() + ": You have summoned the server admin." +
                                " A notification has been sent to his phone.");
                    } else if (message.startsWith("hello")) {
                        TS3Bot.bot.api.sendServerMessage("Hello " + e.getInvokerName() + "!");
                    } else if (message.equals("!channel")) {
                        final Map<ChannelProperty, String> properties = new HashMap<>();
                        properties.put(ChannelProperty.CHANNEL_FLAG_TEMPORARY, "1");
                        properties.put(ChannelProperty.CPID, "12"); // Make it a child of "private channels" (CID: 12)
                        properties.put(ChannelProperty.CHANNEL_DELETE_DELAY, "1800"); // Delete after 30 min if left empty
                        properties.put(ChannelProperty.CHANNEL_CODEC_QUALITY, "7");
                        if (TS3Bot.bot.api.getChannelByNameExact(e.getInvokerName(), true) == null) {
                            TS3Bot.bot.api.createChannel(e.getInvokerName(), properties);
                            Channel newChan = TS3Bot.bot.api.getChannelByNameExact(e.getInvokerName(), false);
                            int userDbId = TS3Bot.bot.api.getDatabaseClientByUId(e.getInvokerUniqueId()).getDatabaseId();
                            TS3Bot.bot.api.moveQuery(1);
                            TS3Bot.bot.api.setClientChannelGroup(5, newChan.getId(), userDbId);
                            TS3Bot.bot.api.sendServerMessage(e.getInvokerName() + ": A channel in your name has been" +
                                    " added. Right click your channel to change its properties.");
                        } else {
                            TS3Bot.bot.api.sendServerMessage(e.getInvokerName() + ": There already exist a channel in" +
                                    " your name.");
                        }
                    }
                }
            }
        });
    }

    public void startClientJoinListener() {
        registerServerEvent();

        TS3Bot.bot.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientJoin(ClientJoinEvent e) {
                if (TS3Bot.bot.api.getClientInfo(e.getClientId()).getType() == 0) {
                    TS3Bot.bot.api.sendPrivateMessage(e.getClientId(), "Welcome to the ouagadougou server." +
                            " I am a bot here to make certain tasks automated. To use me" +
                            ", type [b]!help[/b] in server chat");

                    ClientsConnected clients = new ClientsConnected();
                    PushMessage pushMessage = new PushMessage();

                    if (TS3Bot.bot.api.getClientInfo(e.getClientId()).getDatabaseId() == 12) {
                        pushMessage.push(e.getClientNickname() + " has joined the teamspeak server. \n\n" +
                                "Current clients connected are:\n" + clients.get(), MessagePriority.HIGH);
                    } else {
                        pushMessage.push(e.getClientNickname() + " has joined the teamspeak server. \n\n" +
                                "Current clients connected are:\n" + clients.get());
                    }
                    TS3Bot.clientDb.getClientMap().put(e.getClientId(), TS3Bot.bot.api.getClientInfo(e.getClientId())); // TODO: Check if this line is correct.
                    // TODO: Create ClientIdle class
                }
            }
        });
    }

    public void clientMoved() {
        // TODO: Create clientMoved listener
    }

    public void startClientLeaveListener() {
        registerServerEvent();

        TS3Bot.bot.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientLeave(ClientLeaveEvent e) {
                if (TS3Bot.clientDb.getClientMap().get(e.getClientId()).getType() == 0) {
                    String nickname = TS3Bot.clientDb.getClientMap().get(e.getClientId()).getNickname();
                    int databaseId = TS3Bot.clientDb.getClientMap().get(e.getClientId()).getDatabaseId();
                    TS3Bot.clientDb.getClientMap().remove(e.getClientId()); // Remove client who left from clientDb

                    ClientsConnected clients = new ClientsConnected();
                    PushMessage pushover = new PushMessage();

                    if (databaseId == 12) {
                        pushover.push(nickname + " just left the server.\n\n" +
                                "Clients remaining are:\n" + clients.get(), MessagePriority.HIGH);

                    } else {
                        pushover.push(nickname + " just left the server.\n\n" +
                                "Clients remaining are:\n" + clients.get());
                    }
                    TS3Bot.clientDb.update();
                }
            }
        });

    }

    private void registerServerEvent () {
        if (!serverEvent) {
            TS3Bot.bot.api.registerEvent(TS3EventType.SERVER);
            serverEvent = true;
        }
    }

}
