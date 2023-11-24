package unam;

import java.io.IOException;
import java.util.HashMap;

public interface IFileReader {
    public String readCsv(String source) throws IOException;

    public HashMap<String, Double> csvToHashMap(String source) throws IOException;

    public String[] getRows(String source) throws IOException;
}