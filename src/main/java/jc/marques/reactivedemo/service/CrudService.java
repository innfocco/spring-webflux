package jc.marques.reactivedemo.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudService <T>{
    Mono<T> create(T subject);
    Mono<T> read(String id);
    Flux<T> readAll();
    Mono<T> update(String id, T subject);
    Mono<Void> delete(String id);
}
