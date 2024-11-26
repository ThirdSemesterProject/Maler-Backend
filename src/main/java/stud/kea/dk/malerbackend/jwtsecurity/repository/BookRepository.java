package stud.kea.dk.malerbackend.jwtsecurity.repository;

import stud.kea.dk.malerbackend.jwtsecurity.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
