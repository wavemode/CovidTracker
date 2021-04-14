package TwilioAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


// make a request for sms
public class SmsService {

    private final String sender;


    @Autowired
    public SmsService(@Qualifier("twilio") String sender) {
        this.sender = sender;
    }
// send the message by creating a new number
    public void sendTwilioSms(String number, String message) {
        new TwilioReceiver(number,message);

    }
}
