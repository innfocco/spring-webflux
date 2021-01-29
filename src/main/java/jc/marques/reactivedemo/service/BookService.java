package jc.marques.reactivedemo.service;

import jc.marques.reactivedemo.model.Book;
import jc.marques.reactivedemo.repository.BookRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService implements CrudService<Book> {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Book> create(Book book) {
        return this.repository.insert(book);
    }

    @Override
    public Mono<Book> read(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Flux<Book> readAll() {
        return this.repository.findAll();
    }

    @Override
    public Mono<Book> update(String id, Book book) {
        return this.repository.findById(id)
                .flatMap(
                        foundedBook -> {
                            foundedBook.setAuthor(book.getAuthor());
                            foundedBook.setEdition(book.getEdition());
                            foundedBook.setTitle(book.getTitle());
                            return this.repository.save(foundedBook);
                        }
                );
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id);
    }
}
