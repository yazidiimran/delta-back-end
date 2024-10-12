package net.yazidi.delta.web;

import net.yazidi.delta.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractController<T,ID> implements IController <T,ID>{

    abstract IService getService();

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T t){
        return new ResponseEntity<T>((T) this.getService().save(t), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@RequestBody T t,@PathVariable ID id){
        return new ResponseEntity<T>((T) this.getService().save(t), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id){
        this.getService().deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable ID id){
        return new ResponseEntity<T>((T) this.getService().findById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll(){
        return new ResponseEntity<>(this.getService().findAll(),HttpStatus.OK);
    }

}
