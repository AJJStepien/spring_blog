package pl.sda.spring.blog.springblog.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    @NotBlank (message = "title can't be empty")
    private String title;
    @Type(type = "text")
    @NotBlank(message = "content can't be empty")
    private String content;
    private Category cathegory;
    private LocalDateTime dateTimeAdded;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User author;


    public Post(String title, String content, Category cathegory, User author) {
        this.title = title;
        this.content = content;
        this.cathegory = cathegory;
        this.author = author;
        this.dateTimeAdded = LocalDateTime.now();


    }

    public Post() {

    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCathegory() {
        return cathegory;
    }

    public void setCathegory(Category cathegory) {
        this.cathegory = cathegory;
    }

    public LocalDateTime getDateTimeAdded() {
        return dateTimeAdded;
    }

    public void setDateTimeAdded(LocalDateTime dateTimeAdded) {
        this.dateTimeAdded = dateTimeAdded;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
