package stud.kea.dk.malerbackend.jwtsecurity.repository;


import stud.kea.dk.malerbackend.jwtsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUsername(String name);
}
