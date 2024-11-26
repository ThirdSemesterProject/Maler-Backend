package stud.kea.dk.malerbackend.jwtsecurity.service;

import stud.kea.dk.malerbackend.jwtsecurity.entity.User;

import java.util.List;

public interface IUserService extends ICrudService<User,Long>{
    List<User> findByName(String name);
}
