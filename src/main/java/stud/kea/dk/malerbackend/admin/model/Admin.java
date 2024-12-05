package stud.kea.dk.malerbackend.admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Admin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    String username;
    String password;

    public Admin() {

    }
}
