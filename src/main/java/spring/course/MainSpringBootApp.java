package spring.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import spring.course.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages = { "spring.course" })
public class MainSpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(MainSpringBootApp.class, args);
	}
}
