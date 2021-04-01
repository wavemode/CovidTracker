import org.json.simple.JSONObject;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        var michiganData = new JSONObject();
        michiganData.put("population", 9987032);
        michiganData.put("dailyCases", 1202);
        michiganData.put("totalCases", 98543);

        var arizonaData = new JSONObject();
        arizonaData.put("population", 28394023);
        arizonaData.put("dailyCases", 22439);
        arizonaData.put("totalCases", 18923423);

        var floridaData = new JSONObject();
        floridaData.put("population", 129313283);
        floridaData.put("dailyCases", 2384);
        floridaData.put("totalCases", 55463);

        get("/MI", (req, res) -> {
            res.type("application/json");
            return michiganData.toJSONString();
        });

        get("/FL", (req, res) -> {
            res.type("application/json");
            return floridaData.toJSONString();
        });

        get("/AZ", (req, res) -> {
            res.type("application/json");
            return arizonaData.toJSONString();
        });

    }


}
