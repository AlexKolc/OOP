import java.util.HashMap;
import java.util.Set;

public class EngineObject {
    protected HashMap<String, String> properties;

    EngineObject() {
        properties = new HashMap();
    }


    public int match(Query query) {
        Set thisKeys = this.properties.keySet();
        for (Object item : thisKeys) {
            if (!this.properties.get(item).equals(query.getProperties().get(item))
                    && this.properties.get(item) != null && query.getProperties().get(item) != null) {
                return -1;
            }
        }
        return 0;
    }
}

