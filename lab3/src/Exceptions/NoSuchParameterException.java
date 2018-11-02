package Exceptions;

public class NoSuchParameterException extends IniException {
    private String parameterName;

    public NoSuchParameterException(final String parameterName) {
        super("Parameter " + parameterName + " not found");
        this.parameterName = parameterName;
    }

    public final String getParameterName() {
        return parameterName;
    }
}
