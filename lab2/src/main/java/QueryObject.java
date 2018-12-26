import java.util.HashMap;

public class QueryObject {
    private HashMap<String, String> properties;

    QueryObject() {
        properties = new HashMap<>();
    }

    public void setProperties(HashMap properties) {
        this.properties = properties;
    }

    public HashMap getProperties() {
        return properties;
    }
}
