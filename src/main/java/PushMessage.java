import net.pushover.client.*;

public class PushMessage {
    private final String pushoverApi;
    private final String pushoverUserId;

    public PushMessage() {
        this.pushoverApi = TS3Bot.bot.config.pushoverApi;
        this.pushoverUserId = TS3Bot.bot.config.pushoverUserId;
    }

    void push(String message){
        PushoverClient client = new PushoverRestClient();

        try{
            client.pushMessage(PushoverMessage.builderWithApiToken(this.pushoverApi)
                    .setUserId(this.pushoverUserId)
                    .setMessage(message)
                    .build());
        }catch (PushoverException e){
            System.err.println(e.getMessage());
        }
    }

    void push(String message, MessagePriority messagePriority){
        PushoverClient client = new PushoverRestClient();

        try{
            client.pushMessage(PushoverMessage.builderWithApiToken(this.pushoverApi)
                    .setUserId(this.pushoverUserId)
                    .setMessage(message)
                    .setPriority(messagePriority)
                    .build());
        }catch (PushoverException e){
            System.err.println(e.getMessage());
        }
    }
}
