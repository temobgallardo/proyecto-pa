package unam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FilReaderService implements IFileReaderService {
    public static String NEW_LINE = "\r\n";

    @Override
    public String readCsv(String source) throws IOException {
        if (source == null)
            throw new IllegalArgumentException(source);

        Path sourcePath = Paths.get(source);

        if (!Files.exists(sourcePath))
            throw new FileNotFoundException(source);

        return new String(Files.readAllBytes(sourcePath));
    }

    @Override
    public HashMap<String, Double> csvToHashMap(String source) throws IOException {
        String data = readCsv(source);

        String[] csvRows = data.split(NEW_LINE);

        HashMap<String, Double> dataDic = new HashMap<>();

        for (int i = 1; i < csvRows.length; i++) {
            String[] row = csvRows[i].split(",");
            dataDic.put(row[0], Double.parseDouble(row[2]));
        }

        return dataDic;
    }

    public String[] getRows(String source) throws IOException {
        String data = readCsv(source);

        String[] csvRows = data.split(NEW_LINE);

        return csvRows;
    }
}
