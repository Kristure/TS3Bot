import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;

import java.util.HashMap;
import java.util.Map;

public class TS3Listener {
    public void chatListener() {
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
                        // TODO: Create class for pushover
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
}
