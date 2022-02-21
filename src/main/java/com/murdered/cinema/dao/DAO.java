package com.murdered.cinema.dao;

import java.util.List;

public interface DAO<T> {

    T save(T t);

    void update(T t, String[] params);

    T get(long id);

    void delete(int id);

    List<T> getAll();
}
