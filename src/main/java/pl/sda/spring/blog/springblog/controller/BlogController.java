package pl.sda.spring.blog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.sda.spring.blog.springblog.service.BlogService;

@Controller
public class BlogController {
    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    public String home(){
        return "index";
    }
}
