package old;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public final class Loader {
    public static Path getFilePath(String fileName) {
        return Paths.get(Loader.class.getClassLoader().getResource(fileName).getFile());
    }

    public static String getFileContent(Path path) {
        StringBuilder fileContent = new StringBuilder();
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(fileContent::append);
        } catch (IOException exception) {
            System.err.println("Unknown file's path " + path);
            exception.printStackTrace();
        } finally {
            System.out.println(fileContent);
        }
        return fileContent.toString();
    }
}