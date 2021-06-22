package be.switchfully.dao;

import java.util.Optional;

public interface CustomerDao<T> {

    Optional<T> findByName(String firstName, String lastName);

    Optional<T> findById(String id);

    void save(T t);

    void delete(T t);
}
