package Exceptions;

public class SectionNotFoundException extends IniException{
    private String sectionName;

    public SectionNotFoundException(final String sectionName) {
        super("Section " + sectionName + " not found");
        this.sectionName = sectionName;
    }

    public final String getSectionName() {
        return sectionName;
    }
}
