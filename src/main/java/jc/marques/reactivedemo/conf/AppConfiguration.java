package jc.marques.reactivedemo.conf;

import jc.marques.reactivedemo.model.Book;
import jc.marques.reactivedemo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class AppConfiguration {

    @Bean
    CommandLineRunner fillDatabase(BookRepository repository){
        return args -> {
            Flux<Book> bookFlux = Flux.just(
                    new Book(null, "O Homem que calculava", "Malba Tahan", 1),
                    new Book(null, "A Bussola de Ouro", "Philip Pullman", 1),
                    new Book(null, "A Guerra dos Tronos", "George RR Martin", 1),
                    new Book(null, "Tom Sawyer", "Mark Twain", 1),
                    new Book(null, "Cronicas de Narnia", "C S Lewis", 1),
                    new Book(null, "O Senhor dos Aneis", "J R R Tolkien", 1)
            ).flatMap(book -> repository.save(book));
            //).flatMap(repository::save); <- maneira simplificada

            bookFlux.thenMany(repository.findAll())
                    .subscribe(System.out::println);
        };
    }
}
