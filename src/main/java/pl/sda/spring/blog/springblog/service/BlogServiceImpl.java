package pl.sda.spring.blog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.spring.blog.springblog.model.User;
import pl.sda.spring.blog.springblog.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    private UserRepository userRepository;

    @Autowired
    public BlogServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean addUser(User user) {
        if (userRepository.findFirstByEmail(user.getEmail()) != null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(long userId) {
        userRepository.deleteById(userId);
        return !userRepository.existsById(userId);
    }

    @Override
    @Transactional
    public boolean updatePassword(long userId, String password) {
        return userRepository.findById(userId)
                .flatMap(user ->
                {
                    user.setPassword(password);
                    userRepository.save(user);
                    return Optional.of(true);
                })
                .orElse(false);
//        return userRepository.findById(userId).get().getPassword() == password;
    }

    @Override
    public List<User> getAllUsersOrderByRegDateDesc() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return userRepository.findById(userId);
    }
}
