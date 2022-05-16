package com.murdered.cinema.dao;

import java.util.List;

public interface DAO<T> {

    T save(T t);

    T get(long id);

    void delete(int id);

    List<T> getAll();
}
