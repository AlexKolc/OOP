import java.util.HashMap;

public class QueryBuilder {
    private Query query;

    public QueryBuilder setProperty(String name, String value) {
        this.query.properties.put(name, value);
        return this;
    }

    public void setProperties(HashMap properties) {
        this.query.properties = properties;
    }

    private void createNewQuery() {
        query = new Query();
    }

    QueryBuilder() {
        createNewQuery();
    }

    public Query build() {
        return query;
    }
}

