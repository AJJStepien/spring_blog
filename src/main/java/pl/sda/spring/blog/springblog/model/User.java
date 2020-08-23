package pl.sda.spring.blog.springblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import pl.sda.spring.blog.springblog.model.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @NotBlank(message = "field name is mandatory")
    private String name;
    @NotBlank(message = "field last name is mandatory")
    private String lastName;
    @Email(message = "email is not valid")
    @NotBlank(message = "field email is mandatory")
    private String email;
    @Size(min = 6, max = 255, message = "password requires at least {min} characters (not more than {max})")
    private String password;
    @CreationTimestamp
    private LocalDateTime regDateTime;
    private boolean status;
    @ManyToMany
    private Set<Role> roleSet = new HashSet<>();

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.regDateTime = LocalDateTime.now();
        this.status = true;
    }
}
