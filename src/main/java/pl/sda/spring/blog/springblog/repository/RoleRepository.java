package pl.sda.spring.blog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring.blog.springblog.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findFirstByRoleName(String roleName);
}
