package com.example.CovidTracker.TwilioAPI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TwilioNumberRequestTest {


    @Test
   public void testNumberEnterByUserNotNullValues() {
        //given
        String number = "+15869358566";
        String sms  = "Testing....";

        //when
        TwilioNumberRequest expected= new TwilioNumberRequest(number,sms);

        //then
        assertNotNull(number,expected.getNumber());
        assertNotNull(sms,expected.getSms());


    }


}