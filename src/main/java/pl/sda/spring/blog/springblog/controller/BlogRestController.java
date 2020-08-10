package pl.sda.spring.blog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.spring.blog.springblog.model.Cathegory;
import pl.sda.spring.blog.springblog.model.Post;
import pl.sda.spring.blog.springblog.model.User;
import pl.sda.spring.blog.springblog.service.BlogService;

import java.util.List;

@RestController       //kontroler generujacy wyniki w postaci rest api
//@Controller     //kontroler komunikujący się z warstwą front-endu
public class BlogRestController {
    private BlogService blogService;
    private BlogController blogController;

    @Autowired
    public BlogRestController(BlogService blogService) {
        this.blogService = blogService;
    }
    @Autowired
    public void setBlogController(BlogController blogController) {
        this.blogController = blogController;
    }

    @GetMapping("/hello")
    public String hello() {
        return blogController.home();
    }

    @GetMapping("/hello/{me}")
    public String helloMe(@PathVariable String me) {
        return "hello " + me;
    }

    //żądanie dodania nowego użytkownika do tabeli user
    @PostMapping("/addUser")
    public boolean addUser(@RequestParam("name") String name,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password) {


        return blogService.addUser(new User(name, lastName, email, password));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return blogService.getAllUsersOrderByRegDateDesc();
    }

    @PostMapping("/deleteUser")
    public boolean deleteUserById(@RequestParam("userId") long userId) {
        return blogService.deleteUser(userId);
    }

    @GetMapping("user/{userId}")
    public String getUserById(@PathVariable long userId) {
        if (blogService.getUserById(userId).isPresent()) {
            return blogService.getUserById(userId).get().toString();
        } else return "brak użytkownika o ID = " + userId;
    }
    @PostMapping("user/{userId}/update")
    public boolean updatePassword(@PathVariable long userId,
                                  @RequestParam("password") String password){
        return blogService.updatePassword(userId, password);
    }
    @PostMapping("/newPost")
    public Post createNewPost(@RequestParam("userID") long userId,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("Cathegory") Cathegory cathegory){
        return blogService.addPostByUser(userId, title, content, cathegory);
    }
    @PostMapping("/posts")
    public List<Post> getAllPosts(){
        return blogService.getAllPostOrderByDateDesc();
    }
}
