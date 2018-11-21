package com.example.spring5webapp.bootstrap;

import com.example.spring5webapp.model.Author;
import com.example.spring5webapp.model.Book;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        //Eric Evards
        Author ericEvards = new Author("Eric", "Evards");
        Book ddd = new Book("Domain-Driven Design: Tackling Complexity in the Heart of Software", "978-0321125217", "Addison-Wesley Professional");
        ericEvards.getBooks().add(ddd);
        ddd.getAuthors().add(ericEvards);

        authorRepository.save(ericEvards);
        bookRepository.save(ddd);

        //Rod Jonson
        Author rodJonson = new Author("Rod", "Jonson");
        Book j2ee = new Book("Expert One-on-One J2EE Design and Developmen", "978-0764543852", "Wrox");
        rodJonson.getBooks().add(j2ee);

        authorRepository.save(rodJonson);
        bookRepository.save(j2ee);

    }


}
