package CSC4410.CovidTracker.operation.twilio;

public interface SenderInterface {

    void sendTwilioSms(TwilioNumberRequest receiver);
}
