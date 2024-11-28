package stud.kea.dk.malerbackend.jwtsecurity.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Column(name = "local_time", columnDefinition = "TIME")
    private LocalTime localTime;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.localTime = LocalTime.now(); // Automatically set to the current time
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}
