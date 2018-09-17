package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/navbar").setViewName("navbar");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/disputes").setViewName("disputes");
        registry.addViewController("/groceries").setViewName("disputes");
        registry.addViewController("/chores").setViewName("chores");
        registry.addViewController("/purchases").setViewName("purchases");
    }
    
}
