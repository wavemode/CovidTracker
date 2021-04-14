package CSC4410.CovidTracker.TwilioAPI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


// create the bean
// create the account at twilio and the trial number needed for communication
@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfig {
    private String account;
    private String authentication;
    private String trialNumber;

    public TwilioConfig() {
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(String trialNumber) {
        this.trialNumber = trialNumber;
    }


}
