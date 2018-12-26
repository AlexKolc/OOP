import java.util.HashMap;

public class QueryBuilder {
    private Query query;

    public QueryBuilder setProperty(String name, String value) {
        this.query.getProperties().put(name, value);
        return this;
    }

    public void setProperties(HashMap properties) {
        query.setProperties(properties);
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

