package pl.sda.spring.blog.springblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    private String title;
    @Type(type = "text")
    private String content;
    private Cathegory cathegory;
    private LocalDateTime dateTimeAdded;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User author;


    public Post(String title, String content, Cathegory cathegory, User author) {
        this.title = title;
        this.content = content;
        this.cathegory = cathegory;
        this.author = author;
        this.dateTimeAdded = LocalDateTime.now();


    }
}
