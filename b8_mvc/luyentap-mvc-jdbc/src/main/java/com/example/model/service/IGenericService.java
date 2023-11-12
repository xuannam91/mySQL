package com.example.model.service;

import java.util.List;

public interface IGenericService<T, ID> {
    List<T> findAll();
    Boolean save(T t);
    T find(ID id);
    Boolean update(T t, ID id);
    void delete(ID id);
}
