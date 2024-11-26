package stud.kea.dk.malerbackend.jwtsecurity.service;

import stud.kea.dk.malerbackend.jwtsecurity.entity.Book;
import stud.kea.dk.malerbackend.jwtsecurity.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        // Observe the amount of extra information in the JSON response.
        // We probably only need totalPages, so you could create a new DTO class with book info and totalPages.
        // books.getTotalPages();
        // books.getTotalElements();
        return books;
    }
}
