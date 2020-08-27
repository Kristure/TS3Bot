import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bot {
    private String path;
    public TS3Query ts3Query;
    public TS3Api api;
    public Config config;

    public Bot(String path) {
        this.path = path;
        this.connect();
        this.getApi();
    }

    public void connect() {
        try {
            Gson json = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(this.path));
            config = json.fromJson(reader, Config.class);

            final TS3Config ts3Config = new TS3Config();
            ts3Config.setHost(config.server);
            ts3Config.setQueryPort(config.queryPort);
            if (config.floodRateUnlimited)
                ts3Config.setFloodRate(TS3Query.FloodRate.UNLIMITED);
            if (config.setEnableCommunicationsLogging)
                ts3Config.setEnableCommunicationsLogging(true);

            final TS3Query query = new TS3Query(ts3Config);
            query.connect();

            this.ts3Query = query;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getApi() {
        final TS3Api api = ts3Query.getApi();
        api.login(config.queryUsername, config.queryPassword);
        api.selectVirtualServerById(1);
        api.setNickname("ArmandBot");
        this.api = api;
    }
}
