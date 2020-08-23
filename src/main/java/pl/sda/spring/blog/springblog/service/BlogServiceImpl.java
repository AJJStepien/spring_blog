package pl.sda.spring.blog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sda.spring.blog.springblog.model.Category;
import pl.sda.spring.blog.springblog.model.Post;
import pl.sda.spring.blog.springblog.model.Role;
import pl.sda.spring.blog.springblog.model.User;
import pl.sda.spring.blog.springblog.repository.PostRepository;
import pl.sda.spring.blog.springblog.repository.RoleRepository;
import pl.sda.spring.blog.springblog.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private RoleRepository roleRepository;

    @Autowired
    public BlogServiceImpl(UserRepository userRepository, PostRepository postRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean addUser(User user) {
        if (userRepository.findFirstByEmail(user.getEmail()) == null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Post> getPostById(long postId) {
        return postRepository.findById(postId);
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

    @Override
    public Post addPostByUser(long userId, String title, String content, Category cathegory) {
        if (userRepository.existsById(userId)) {
            User author = userRepository.findById(userId).get();
            return postRepository.save(new Post(title, content, cathegory, author));
        }
        return null;
    }

    @Override
    public List<Post> getAllPostOrderByDateDesc() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "dateTimeAdded"));
    }

    @Override
    public User addRoleToUser(User user, String roleName) {
        Role role =  roleRepository.findFirstByRoleName(roleName);
        return user;
    }
}
