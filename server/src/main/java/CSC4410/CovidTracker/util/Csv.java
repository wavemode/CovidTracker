package CSC4410.CovidTracker.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Utility methods for parsing CSV data.
 */
public class Csv {
    public static ArrayList<CSVRecord> parseCsv(String data) throws IOException {
        var csvReader = new StringReader(data);
        var result = new ArrayList<CSVRecord>();

        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();

        var iter = format.parse(csvReader);
        for (CSVRecord record : iter) {
            result.add(record);
        }

        return result;
    }


    private Csv() {}
}
