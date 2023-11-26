package tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import unam.FilReaderService;
import unam.IFileReaderService;

@RunWith(Parameterized.class)
public class FileReader_Test {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new String[][] {
                { null }, { "" }, { "wrong_path.csv" }, { "1000" }
        });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ String wrongPath;

    @Test
    public void readFile() {
        String sourcePath = "porcino_avicola.csv";
        IFileReaderService sut = new FilReaderService();
        String csv = null;

        try {
            csv = sut.readCsv(sourcePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(csv);
    }

    @Test
    public void readFile_throwException() {
        String sourcePath = wrongPath;
        IFileReaderService sut = new FilReaderService();

        Assert.assertThrows(Exception.class, () -> sut.readCsv(sourcePath));
    }

    @Test
    public void csvToDictionary() {
        String sourcePath = "porcino_avicola.csv";
        IFileReaderService sut = new FilReaderService();
        HashMap<String, Double> csv = null;

        try {
            csv = sut.csvToHashMap(sourcePath);
        } catch (Exception e) {

        }

        Assert.assertNotNull(csv);
    }

    @Test
    public void readCsvRowsString() {
        String sourcePath = "porcino_avicola.csv";
        IFileReaderService sut = new FilReaderService();
        String[] actualResult = new String[] {};

        try {
            actualResult = sut.getRows(sourcePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(actualResult);
        Assert.assertTrue(actualResult.length == 11);
    }
}
