package be.switchfully.dao;

import java.util.List;

public interface ReceiptDao<T>{

    List<T> getAll();

    void save(T t);

    void delete(T t);
}
