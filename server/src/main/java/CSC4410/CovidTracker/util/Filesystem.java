package CSC4410.CovidTracker.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Filesystem {
    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
