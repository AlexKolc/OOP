package Exceptions;

public class FileNotFoundException extends IniException {
    private String fileName;

    public FileNotFoundException(final String fileName) {
        super(fileName + " doesn't exist or can't be read");
        this.fileName = fileName;
    }

    public final String getFileName() {
        return fileName;
    }
}
