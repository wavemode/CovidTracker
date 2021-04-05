package TwilioAPI;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


// initialize the application
public class TwilioInit {
    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInit.class);

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioInit(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        Twilio.init(
                twilioConfig.getAccount(),
                twilioConfig.getAuthentication()
        );
        LOGGER.info("Application started ... with account ", twilioConfig.getAccount());
    }
}

