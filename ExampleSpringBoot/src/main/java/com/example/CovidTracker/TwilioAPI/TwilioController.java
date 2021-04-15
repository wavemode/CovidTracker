package com.example.CovidTracker.TwilioAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TwilioController {

    private final SmsService service;

    @Autowired
    public TwilioController(SmsService service) {
        this.service = service;
    }
    //  path to create a new valid number
    @RequestMapping (value = "/twilio/sms",method = RequestMethod.POST)
    public void sentTwilioSms(@RequestBody TwilioNumberRequest numberRequest){
        service.sendTwilioSms(numberRequest);
    }

}
