package mosaic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Baeldung
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;
import mosaic.modules.baeldung.entities.User;
import mosaic.modules.baeldung.repositories.UserRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/starter-api"); // se coloca el mismo path que war generado (para que sea indistinto ejecutarlo local o desde un servidor)
		SpringApplication.run(Application.class, args);
	}
	
	//Baeldung
	@Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            Stream.of("Juan", "Marina", "Felipe", "Fernanda", "Lucas").forEach(name -> {
                User user = new User(name, name.toLowerCase() + "@sieteideas.com.ar");
                userRepository.save(user);
            });
            userRepository.findAll().forEach(System.out::println);
        };
    }

}
