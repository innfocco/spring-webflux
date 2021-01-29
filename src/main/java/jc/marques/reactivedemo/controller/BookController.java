package jc.marques.reactivedemo.controller;

import jc.marques.reactivedemo.model.Book;
import jc.marques.reactivedemo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service){
        this.service = service;
    }

    @GetMapping
    public Flux<Book> getAllBooks(){
        return this.service.readAll().log();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable(value = "id") String id){
        return this.service.read(id)
                .map(book -> ResponseEntity.ok(book))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> save(@RequestBody Book book){
        return this.service.create(book);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Book>> update(@PathVariable(value = "id") String id, @RequestBody Book book){
        return this.service.update(id, book)
                .map(b -> ResponseEntity.ok(b))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable(value = "id") String id){
        return this.service.delete(id);
    }

}
