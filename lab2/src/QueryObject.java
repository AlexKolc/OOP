import java.util.HashMap;
import java.util.Set;

public class QueryObject {
    protected HashMap<String, String> properties;

    QueryObject() {
        properties = new HashMap();
    }

    public HashMap getProperties() {
        return properties;
    }
}
