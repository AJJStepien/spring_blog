package pl.sda.spring.blog.springblog.service;

import pl.sda.spring.blog.springblog.model.User;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    //CRUD
    //dodaj, usuń, zmien haslo, pobierz wszystkich, pobierz jednego po id
    boolean addUser(User user);
    boolean deleteUser(long userId);
    boolean updatePassword(long userId, String password);
    List<User> getAllUsersOrderByRegDateDesc();
    Optional<User> getUserById(long userId);

}
