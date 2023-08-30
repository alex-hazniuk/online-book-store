package com.example.onlinebookstore;

import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBookStoreApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book predator = new Book();
            predator.setTitle("Predator");
            predator.setDescription("science fiction");
            predator.setAuthor("Paul Monette");
            predator.setCoverImage("colored");
            predator.setIsbn("345629980");
            predator.setPrice(BigDecimal.valueOf(99));
            bookService.save(predator);
            System.out.println(bookService.findAll());
        };
    }
}
