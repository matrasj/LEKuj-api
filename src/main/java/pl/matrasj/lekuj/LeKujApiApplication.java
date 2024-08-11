package pl.matrasj.lekuj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api/test")
public class LeKujApiApplication {

    @GetMapping
    public String xd() {
        return "XD";
    }
    public static void main(String[] args) {
        SpringApplication.run(LeKujApiApplication.class, args);
    }

}
