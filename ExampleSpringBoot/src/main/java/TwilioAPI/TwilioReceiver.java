package TwilioAPI;

public class TwilioReceiver {


    private final String number, sms;


    public TwilioReceiver(String number, String sms) {
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
