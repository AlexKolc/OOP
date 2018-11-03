package Exceptions;

public class NumberFormatException extends IniException {
    private String parameter;

    public NumberFormatException(final String parameter) {
        super("Impossible to cast a string \"" + parameter + "\" to type");
        this.parameter = parameter;
    }

    public final String getParameterName() {
        return parameter;
    }
}
