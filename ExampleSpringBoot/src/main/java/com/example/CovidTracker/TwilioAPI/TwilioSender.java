package com.example.CovidTracker.TwilioAPI;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            PhoneNumber from = trialNumber();
            System.out.println(from);
            String sms = numberRequest.getSms();
            createMessage(sendTo,from,sms).create();
            LOGGER.info("Send sms ", numberRequest);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + numberRequest.getNumber() + "] is not correct"
            );
        }

    }
    public MessageCreator createMessage(PhoneNumber sendTo,PhoneNumber from, String sms){
        MessageCreator creator  = Message.creator(sendTo,from,sms);
        return creator;
    }
    public PhoneNumber trialNumber(){
        return new PhoneNumber(twilioConfig.getTrialNumber());
    }


    //check if the phone number entered by the user is valid
    protected boolean isPhoneNumberValid(String phoneNumber) {//have not finished yet
        String regex = ("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if(matcher.matches()){
            return true;
        }
        return false;
    }


}


