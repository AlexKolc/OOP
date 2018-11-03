import java.io.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import Exceptions.*;
import Exceptions.FileNotFoundException;
import Exceptions.NumberFormatException;

public class Ini {
    private Map<String, Map<String, String>> parsedData;
    private boolean isInitialised;

    Ini(final String fileName) throws FileNotFoundException, IOException {
        if (!(new File(fileName)).exists()) {
            throw new FileNotFoundException(fileName);
        }
        isInitialised = false;
        parsedData = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName)); // FIXED: Scanner changed on BufferedReader
        String curSection = "";
        while (bufferedReader.ready()) {
            String curLine = bufferedReader.readLine();
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
        isInitialised = true;
    }

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

    private AbstractMap.SimpleEntry<String, String> extractParameter(String line) { // FIXED: spaces in parameter's value
        String[] parts = line.split("=", 2);
        return new AbstractMap.SimpleEntry<>(parts[0].trim(), parts[1].trim());
    }

    private void assertExistence(final String sectionName, final String parameterName) throws NoSuchParameterException, SectionNotFoundException, ConfigNotInitialisedException {
        if (!isHaveSection(sectionName)) {
            throw new SectionNotFoundException(sectionName);
        }
        if (!isHaveParameter(sectionName, parameterName)) {
            throw new NoSuchParameterException(parameterName);
        }
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

    public int getInteger(final String sectionName, final String parameterName) throws NoSuchParameterException, ConfigNotInitialisedException, SectionNotFoundException, NumberFormatException {
        assertExistence(sectionName, parameterName);
        String stringValue = parsedData.get(sectionName).get(parameterName);
        try {
            return Integer.parseInt(stringValue);
        } catch (Exception ex) {
            throw new NumberFormatException(stringValue);
        }
    }

    public double getDouble(final String sectionName, final String parameterName) throws NoSuchParameterException, ConfigNotInitialisedException, SectionNotFoundException, NumberFormatException {
        assertExistence(sectionName, parameterName);
        String stringValue = parsedData.get(sectionName).get(parameterName);
        try {
            return Double.parseDouble(stringValue);
        } catch (Exception ex) {
            throw new NumberFormatException(stringValue);
        }
    }

    @Override
    public String toString() { // FIXED: ADDED
        StringBuilder sb = new StringBuilder();
        parsedData.forEach((section, parameters) -> {
            sb.append("[").append(section).append("]\n");
            parameters.forEach((key, value) -> sb.append("\t").append(key).append(" = ").append(value).append("\n"));
        });
        return sb.toString();
    }
}
