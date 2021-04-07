package CSC4410.CovidTracker;

import CSC4410.CovidTracker.util.Filesystem;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Application configuration, loaded as JSON from `Config.configFile`.
 */
public class Config {

    private static final String configFile = "config.json";

    static {
        try {
            loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void loadConfig() throws IOException {
        var content = Filesystem.readFile(configFile);
        var json = new JSONObject(content);

        mysqlUser = json.getString("mysqlUser");
        mysqlPassword = json.getString("mysqlPassword");
        mysqlDatabase = json.getString("mysqlDatabase");
        mysqlHost = json.getString("mysqlHost");
        mysqlPort = json.getInt("mysqlPort");

    }

    private Config() {}


    public static String mysqlUser;
    public static String mysqlPassword;
    public static String mysqlHost;
    public static int mysqlPort;
    public static String mysqlDatabase;

}
