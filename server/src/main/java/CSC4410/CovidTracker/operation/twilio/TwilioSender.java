package CSC4410.CovidTracker.operation.twilio;

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
            LOGGER.info("Send sms {}", numberRequest);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + numberRequest.getNumber() + "] is not correct"
            );
        }

    }//check if the phone number entered by the user is valid
    public static boolean isPhoneNumberValid(String phoneNumber)
    {
        // validate phone numbers with format 1234567890
        if (phoneNumber.matches("\\d{10}")) {
            return true;
            // validate phone numbers with -,. or spaces
        }else if (phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return true;
        }else if(phoneNumber.matches("/^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\\s\\./0-9]*$/g")) {
            return true;
        }else
            return false;
    }
}
