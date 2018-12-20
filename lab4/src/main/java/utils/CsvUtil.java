package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<String[]> readFromCsv(String fileName) {
        List<String[]> items = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                items.add(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void writeToCsv(String fileName, List<String[]> items) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             OutputStreamWriter osw = new OutputStreamWriter(fos,
                     StandardCharsets.UTF_8);
             CSVWriter writer = new CSVWriter(osw)) {
            writer.writeAll(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
