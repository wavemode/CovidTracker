package TwilioAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/twilio/sms")
public class TwilioController {

    private final SmsService service;

@Autowired
    public TwilioController(SmsService service) {
        this.service = service;
    }
@PostMapping
    public void sentTwilioSms(@RequestBody TwilioReceiver receiver){
    service.sendTwilioSms(receiver.getNumber(),receiver.getSms());
}

}
