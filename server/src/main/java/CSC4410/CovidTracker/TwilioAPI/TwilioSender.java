package CSC4410.CovidTracker.TwilioAPI;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSender implements SenderInterface
{


    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSender.class);

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioSender(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }
//if the phone number is valid send the message to the appropriate destination
    @Override
    public void sendTwilioSms(TwilioNumberRequest numberRequest) {
        if (isPhoneNumberValid(numberRequest.getNumber())) {
            PhoneNumber sendTo = new PhoneNumber(numberRequest.getNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            String sms = numberRequest.getSms();
            MessageCreator creator = Message.creator(sendTo, from, sms);
            creator.create();
            LOGGER.info("Send sms ", numberRequest);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + numberRequest.getNumber() + "] is not correct"
            );
        }

    }//check if the phone number entered by the user is valid
    private boolean isPhoneNumberValid(String phoneNumber) {//have not finished yet
        return true;
    }


}


