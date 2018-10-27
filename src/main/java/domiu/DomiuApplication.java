package domiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "domiu")
@SpringBootApplication
public class DomiuApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomiuApplication.class, args);
	}
}
