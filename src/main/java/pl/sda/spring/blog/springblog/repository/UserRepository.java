package pl.sda.spring.blog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring.blog.springblog.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);
}
