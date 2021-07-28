package mosaic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/mosaic-starter-api"); // se coloca el mismo path que war generado (para que sea indistinto ejecutarlo local o desde un servidor)
		SpringApplication.run(Application.class, args);
	}

}
