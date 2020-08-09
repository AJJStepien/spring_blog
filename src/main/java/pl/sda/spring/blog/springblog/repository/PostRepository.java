package pl.sda.spring.blog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring.blog.springblog.model.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
