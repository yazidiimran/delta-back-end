package net.yazidi.delta.service;

import java.util.List;

public interface IService<T,ID> {
    List<T> findAll();
    void deleteById(ID id);
    T save(T t);
    T findById(ID id);
}
