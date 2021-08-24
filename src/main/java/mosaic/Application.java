package mosaic;

/* doc: 
 * coloque un archivo cert_1234.pdf en /Fuentes/
 * corra la aplicacion
 * acceda a http://localhost:8080/file-server/file/cert_1234.pdf
 * se muestra el archivo en /Fuentes/cert_1234.pdf
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import mosaic.servlet.FileServlet;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/file-server"); // se coloca el mismo path que war generado (para que sea indistinto ejecutarlo local o desde un servidor)
		SpringApplication.run(Application.class, args);
	}

	/* ref: 
	 * https://www.baeldung.com/register-servlet
	 */
	
	@Bean
	public ServletRegistrationBean exampleServletBean() {
	    ServletRegistrationBean bean = new ServletRegistrationBean(
	      new FileServlet(), "/file/*");
	    bean.setLoadOnStartup(1);
	    return bean;
	}
}
