package com.example.CovidTracker.TwilioAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

// receive the data from the user and create a new number
public class TwilioNumberRequest {


    private final String number;
    private final String sms;


    public TwilioNumberRequest(@JsonProperty("number") String number,
                               @JsonProperty("sms") String sms) {
        this.number = number;
        this.sms = sms;
    }

    public String getNumber() {
        return number;
    }

    public String getSms() {
        return sms;
    }


    @Override
    public String toString() {
        return "TwilioReceiver{" +
                "number='" + number + '\'' +
                ", sms='" + sms + '\'' +
                '}';
    }
}
