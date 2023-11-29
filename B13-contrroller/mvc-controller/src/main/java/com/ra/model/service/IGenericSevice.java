package com.ra.model.service;

import java.util.List;

public interface IGenericSevice <T, ID>{
    List<T> findAll();
    Boolean create(T t);
    Boolean update(T t,ID id);
    T findById(ID id);
    void delete(ID id);
}
