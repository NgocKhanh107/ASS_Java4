package repositories;

import entities.CuaHang;

import java.util.List;

public interface CRUDRepo <T>{
    List<T> getAll();
    T create(T t);
    T findById(String id) throws Exception;
    void update(T t);
    void delete(T t);
}

