package TwilioAPI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


// create the bean
@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfig {
    private String account;
    private String authentication;
    private String number;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "TwilioConfig{" +
                "account='" + account + '\'' +
                ", authentication='" + authentication + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
