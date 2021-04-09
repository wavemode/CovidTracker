package CSC4410.CovidTracker.TwilioAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


// service for sms communication
@org.springframework.stereotype.Service
public class SmsService {

    private final SenderInterface senderService;


    @Autowired
    // inject the sender interface inside the service
    public SmsService(@Qualifier("twilio") TwilioSender sender) {
        this.senderService = sender;
    }
// send the requested number
    public void sendTwilioSms(TwilioNumberRequest numberRequest) {
        senderService.sendTwilioSms(numberRequest);
    }
}
