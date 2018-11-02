package Exceptions;

public class ConfigNotInitialisedException extends IniException {
    public ConfigNotInitialisedException() {
        super("Config wasn't initialized");
    }
}
