package stud.kea.dk.malerbackend.jwtsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponseModel  {
    private final String token;
}