package com.example.spring5webapp.bootstrap;

import com.example.spring5webapp.model.Author;
import com.example.spring5webapp.model.Book;
import com.example.spring5webapp.model.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        //Eric Evards
        Publisher dddPublisher = new Publisher();
        dddPublisher.setName("Addison-Wesley Professional");
        publisherRepository.save(dddPublisher);

        Author ericEvards = new Author("Eric", "Evards");
        Book ddd = new Book("Domain-Driven Design: Tackling Complexity in the Heart of Software", "978-0321125217", dddPublisher);
        ericEvards.getBooks().add(ddd);
        ddd.getAuthors().add(ericEvards);


        authorRepository.save(ericEvards);
        bookRepository.save(ddd);

        //Rod Jonson
        Publisher j2eePublisher = new Publisher();
        j2eePublisher.setName("Wrox");
        publisherRepository.save(j2eePublisher);

        Author rodJonson = new Author("Rod", "Jonson");
        Book j2ee = new Book("Expert One-on-One J2EE Design and Developmen", "978-0764543852", j2eePublisher);
        rodJonson.getBooks().add(j2ee);

        authorRepository.save(rodJonson);
        bookRepository.save(j2ee);

    }


}
