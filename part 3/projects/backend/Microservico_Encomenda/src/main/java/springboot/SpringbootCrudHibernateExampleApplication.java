package springboot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

@EnableEurekaClient
@RestController
public class SpringbootCrudHibernateExampleApplication {
	
 
	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudHibernateExampleApplication.class, args);
	}

	@RequestMapping(value = "/")
	   public String home() {
	      return "Encomenda Cliente";
	}
	
}
