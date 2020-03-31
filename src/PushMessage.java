import net.pushover.client.*;

public class PushMessage {
    private String pushoverApi;
    private String pushoverUserId;

    public PushMessage(String pushoverApi, String pushoverUserId) {
        this.pushoverApi = pushoverApi;
        this.pushoverUserId = pushoverUserId;
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
    void push(String message, int priority){
        PushoverClient client = new PushoverRestClient();
        String pushoverAPI = this.pushoverApi;
        String pushoverUserId = this.pushoverUserId;

        try{
            client.pushMessage(PushoverMessage.builderWithApiToken(pushoverAPI)
                    .setUserId(pushoverUserId)
                    .setMessage(message)
                    .setPriority(MessagePriority.HIGH)
                    .build());
        }catch (PushoverException e){
            System.err.println(e.getMessage());
        }
    }
}
