package com.example.CovidTracker.TwilioAPI;

public interface SenderInterface {

    void sendTwilioSms(TwilioNumberRequest receiver);
}
