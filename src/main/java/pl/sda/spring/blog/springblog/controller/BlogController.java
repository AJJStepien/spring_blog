package pl.sda.spring.blog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.spring.blog.springblog.model.Category;
import pl.sda.spring.blog.springblog.model.Post;
import pl.sda.spring.blog.springblog.model.User;
import pl.sda.spring.blog.springblog.service.BlogService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    private BlogService blogService;
    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String home(Model model){
        //Model - klasa obiektu do przekazywania informacji pomiędzy back-endem oraz front-endem
//        model.addAttribute("nazwa obiektu front-end", "obiekt przekazywany z backend");
        List<Post> posts = blogService.getAllPostOrderByDateDesc();
        model.addAttribute("header_title", "BLOG IT");
        model.addAttribute("header_author", "Adam Stępień");
        model.addAttribute("posts", posts);
        model.addAttribute("cats", Category.values());
        model.addAttribute("newPost", new Post());
        return "index";
    }
    @GetMapping("/posts&{postId}")
    public String getPostById(@PathVariable("postId") long postId, Model model){
        Optional<Post> postOpt = blogService.getPostById(postId);
        if(postOpt.isPresent()){
            model.addAttribute("post", postOpt.get());
            return "post";
        }
        model.addAttribute("post", null);
        return "post";
    }
    @PostMapping("/addPost")
    public String addPost(@Validated @ModelAttribute("newPost") Post newPost,
                          BindingResult bindingResult,
                          Model model){
        if(bindingResult.hasErrors()){
            return "/";
        }
        blogService.addPostByUser(1, newPost.getTitle(), newPost.getContent(), newPost.getCathegory());
        return "redirect:/";
    }
    @GetMapping("/addUser")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("header_title", "REGISTRATION FORM");
        return "registration";
    }
    @PostMapping("/addUser")
    public String addUser(@Valid @ModelAttribute("user") User user,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "registration";
        }
        boolean isRegistred = blogService.addUser(new User(
                user.getName(), user.getLastName(),
                user.getEmail(), user.getPassword()));
        return "redirect:/";
    }
}
