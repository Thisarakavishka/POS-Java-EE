package com.example.pos.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    boolean add(T dto)throws Exception;
    boolean update(T dto)throws Exception;
    boolean delete(String id)throws Exception;
    T search(String id)throws Exception;
    List<T> getAll()throws Exception;
}
