package com.example.demo;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Component;

//import com.example.entity.Roles;
//import com.example.repository.RoleRepository;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
//@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
//@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({
	"com.example.controller", 
	"com.example.service",
	"com.exaple.config", 
	"com.example.exceptions", 
	"com.example.security", 
	"com.example.utils"
	})
@EntityScan("com.example.entity")
@EnableJpaRepositories("com.example.repository")
//@EnableSwagger2
public class SpringBootJpaMyBlogApplication /*implements CommandLineRunner */ {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaMyBlogApplication.class, args);
	}

//	@Autowired
//	public RoleRepository roleRepository;
	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		Roles adminRole = new Roles();
//		adminRole.setName("ROLE_ADMIN");
//		roleRepository.save(adminRole);
//		
//		Roles userRole = new Roles();
//		adminRole.setName("ROLE_ADMIN");
//		roleRepository.save(userRole);
//		
//	}

}
