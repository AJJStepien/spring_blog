package pl.sda.spring.blog.springblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBlogApplication.class, args);
    }

}
