package jc.marques.reactivedemo.repository;

import jc.marques.reactivedemo.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}
