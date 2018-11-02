import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Exceptions.*;

public class IniParser {
    private Map<String, Map<String, String>> parsedData;
    private boolean isInitialised;

    private String deleteComments(String line) {
        int posSemicolon = line.indexOf(";");
        if (posSemicolon != -1) {
            line = line.substring(0, posSemicolon);
        }
        return line;
    }

    private boolean lineIsEmpty(final String line) {
        int minLineLength = 4;
        return line.length() < minLineLength;
    }

    private boolean lineIsSection(final String line) {
        return line.contains("[");
    }

    private String extractSection(String line) { //Get section name
        return line.substring(line.indexOf("[") + 1, line.indexOf("]"));
    }

    private AbstractMap.SimpleEntry<String, String> extractParameter(String line) {
        line = line.replaceAll(" ", "");
//        String[] parts = line.split("=", 2);
//        parts[0].trim();
        String parameter = line.substring(0, line.indexOf('='));
        String value = line.substring(line.indexOf('=') + 1, line.length());
        return new AbstractMap.SimpleEntry<>(parameter, value);
    }

    private void assertExistence(final String sectionName, final String parameterName) throws NoSuchParameterException, SectionNotFoundException, ConfigNotInitialisedException {
        if (!isHaveSection(sectionName)) {
            throw new SectionNotFoundException(sectionName);
        }
        if (!isHaveParameter(sectionName, parameterName)) {
            throw new NoSuchParameterException(parameterName);
        }
    }

    public void initialize(final String fileName) throws FileNotFoundException {
        isInitialised = false;
        parsedData = new HashMap<>();
        String curSection = "";
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String curLine = scanner.nextLine();
                curLine = deleteComments(curLine);
                if (lineIsEmpty(curLine)) {
                    continue;
                }
                if (lineIsSection(curLine)) {
                    curSection = extractSection(curLine);
                    parsedData.put(curSection, new HashMap<>());
                    continue;
                }
                AbstractMap.SimpleEntry<String, String> parameter = extractParameter(curLine);
                parsedData.get(curSection).put(parameter.getKey(), parameter.getValue());
            }
        }  catch (IOException e) {
            throw new FileNotFoundException(fileName);
        }
        isInitialised = true;
    }

    public boolean isHaveSection(final String sectionName) throws ConfigNotInitialisedException {
        if (!isInitialised) {
            throw new ConfigNotInitialisedException();
        }
        return parsedData.containsKey(sectionName);
    }

    public boolean isHaveParameter(final String sectionName, final String parameterName) throws ConfigNotInitialisedException, SectionNotFoundException {
        if (!isInitialised) {
            throw new ConfigNotInitialisedException();
        }
        if (!isHaveSection(sectionName)) {
            throw new SectionNotFoundException(sectionName);
        }
        return parsedData.get(sectionName).containsKey(parameterName);
    }

    public String getString(final String sectionName, final String parameterName) throws NoSuchParameterException, ConfigNotInitialisedException, SectionNotFoundException {
        assertExistence(sectionName, parameterName);
        return parsedData.get(sectionName).get(parameterName);
    }

    public int getInteger(final String sectionName, final String parameterName) throws NoSuchParameterException, ConfigNotInitialisedException, SectionNotFoundException {
        assertExistence(sectionName, parameterName);
        return Integer.parseInt(parsedData.get(sectionName).get(parameterName));
    }

    public double getDouble(final String sectionName, final String parameterName) throws NoSuchParameterException, ConfigNotInitialisedException, SectionNotFoundException {
        assertExistence(sectionName, parameterName);
        return Double.parseDouble(parsedData.get(sectionName).get(parameterName));
    }
    /*public <T> T getValue(final String sectionName, final String parameterName, Class<T> type) throws NoSuchParameterException, ConfigNotInitialisedException, SectionNotFoundException {
        assertExistence(sectionName, parameterName);
        if (type == String.class)
            return type.cast(parsedData.get(sectionName).get(parameterName));
        if (type == Double.class)
            return type.cast(Double.parseDouble(parsedData.get(sectionName).get(parameterName)));
        return type.cast(Integer.parseInt(parsedData.get(sectionName).get(parameterName)));
    }*/

    @Override
    public String toString() {
        return parsedData.forEach((key, value) -> value
                .entrySet().addAll()
                .collect(Collectors.toList());
    }
}
