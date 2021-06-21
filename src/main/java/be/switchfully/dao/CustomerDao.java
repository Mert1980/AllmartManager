package be.switchfully.dao;

import java.util.Optional;

public interface CustomerDao<T> {

    Optional<T> findByName(String name);

    Optional<T> findById(String id);

    void save(T t);

    void delete(T t);
}
