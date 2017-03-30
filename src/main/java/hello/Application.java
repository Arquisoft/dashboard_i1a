package hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("dashboard.model")
@EnableJpaRepositories("dashboard.persistence")
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}