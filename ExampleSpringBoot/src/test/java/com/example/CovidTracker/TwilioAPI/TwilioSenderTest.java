package com.example.CovidTracker.TwilioAPI;

import com.twilio.type.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public  class TwilioSenderTest {

@Autowired
   private final TwilioConfig twilioConfig = new TwilioConfig();

    @Autowired
    TwilioSender testing = new TwilioSender(twilioConfig);



    @Test
public void testUniqueTrialNumber(){
    PhoneNumber trialNumber = new PhoneNumber("+15869358566");

    PhoneNumber expected = testing.trialNumber();
    assertNotEquals(expected,trialNumber);

}


@Test
    void isPhoneNumberValidTest() {
        //given
        String number  = "+1 (586) 935-8566";

        //when
        boolean excpected = testing.isPhoneNumberValid(number);

        //then
        assertThat(excpected).isTrue();


    }
}