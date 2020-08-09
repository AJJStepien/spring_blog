package pl.sda.spring.blog.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController       //kontroler generujacy wyniki w postaci rest api
//@Controller     //kontroler komunikujący się z warstwą front-endu
public class BlogRestController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/hello/{me}")
    public String helloMe(@PathVariable String me){
        return "hello " + me;
    }
}
