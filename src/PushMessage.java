import net.pushover.client.*;

public class PushMessage {
    void push(String message){
        PushoverClient client = new PushoverRestClient();
        String pushoverAPI = "aditr8h74zjadxxpwuc5s8ni28qsg5";
        String pushoverUserId = "ur9nskmnd9cmaeaxvge18v6d1idrc5";

        try{
            client.pushMessage(PushoverMessage.builderWithApiToken(pushoverAPI)
                    .setUserId(pushoverUserId)
                    .setMessage(message)
                    .build());
        }catch (PushoverException e){
            // Do nothing
        }
    }
    void push(String message, int priority){
        PushoverClient client = new PushoverRestClient();
        String pushoverAPI = "aditr8h74zjadxxpwuc5s8ni28qsg5";
        String pushoverUserId = "ur9nskmnd9cmaeaxvge18v6d1idrc5";

        try{
            client.pushMessage(PushoverMessage.builderWithApiToken(pushoverAPI)
                    .setUserId(pushoverUserId)
                    .setMessage(message)
                    .setPriority(MessagePriority.HIGH)
                    .build());
        }catch (PushoverException e){
            // Do nothing
        }
    }
}
