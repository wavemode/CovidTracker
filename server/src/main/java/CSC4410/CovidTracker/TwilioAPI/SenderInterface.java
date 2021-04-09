package CSC4410.CovidTracker.TwilioAPI;

public interface SenderInterface {

    void sendTwilioSms(TwilioNumberRequest receiver);
}
