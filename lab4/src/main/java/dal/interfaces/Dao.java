package dal.interfaces;

import java.util.List;

public interface Dao<T, PK> {
    //create
    void add(T newInstance);

    //read
    List<T> getAll();

    T getById(PK id);

    //update
    void update(T obj);

    //delete
    void delete(T obj);
}
