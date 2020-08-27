import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Map;

public class ClientsConnected {
    private String clients = "";

    public ClientsConnected() {
        TS3Bot.clientDb.update();

        for (Map.Entry<Integer, Client> cli : TS3Bot.clientDb.getClientMap().entrySet()) {
            if (cli.getValue().getType() == 0) {
                String micStatus = "not muted";
                String outputStatus = "not muted";

                if (cli.getValue().isInputMuted())
                    micStatus = "muted";

                if (!TS3Bot.bot.api.getClientInfo(cli.getValue().getId()).isInputHardware())
                    micStatus = "dis";

                if (cli.getValue().isAway()) {
                    micStatus = "away";
                    outputStatus = "away";
                }

                if (cli.getValue().isOutputMuted())
                    outputStatus = "muted";

                if (!TS3Bot.bot.api.getClientInfo(cli.getValue().getId()).isOutputHardware())
                    outputStatus = "dis";

                long timeConnected = TS3Bot.bot.api.getClientInfo(cli.getValue().getId()).getTimeConnected();

                clients = clients.concat(cli.getValue().getNickname() + " (" +
                        TS3Bot.bot.api.getChannelInfo(cli.getValue().getChannelId()).getName() +
                        ") (" + micStatus + " | " + outputStatus + ") (" + ConvertTime.convert(timeConnected) + ")\n");

            }
        }
    }

    public String get() {
        return clients;
    }
}
