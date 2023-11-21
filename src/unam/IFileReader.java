package unam;

import java.io.IOException;
import java.util.HashMap;

public interface IFileReader {
    public String readCsv(String source) throws IOException;

    public HashMap<String, Double> csvToDictionary(String source) throws IOException;
}
