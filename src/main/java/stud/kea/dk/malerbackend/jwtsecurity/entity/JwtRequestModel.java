package stud.kea.dk.malerbackend.jwtsecurity.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestModel  {
    private String username;
    private String password;
}
