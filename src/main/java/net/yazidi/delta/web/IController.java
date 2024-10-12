package net.yazidi.delta.web;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IController <T,ID>{
    ResponseEntity<T> create(T t);
    ResponseEntity<T> update(T t,ID id);
    ResponseEntity<Void> delete(ID id);
    ResponseEntity<T> findById(ID id);
    ResponseEntity<List<T>> findAll();
}
