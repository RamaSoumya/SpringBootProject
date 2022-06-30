package com.example.config;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.stereotype.Component;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.CustomUserDetailsService;

//import com.example.security.JwtAuthenticationEntryPoint;
//import com.example.security.JwtAuthenticationFilter;

@ConditionalOnProperty(name = "management.security.enabled", havingValue = "false")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Component
//@ComponentScan({
//	"com.example.security"
//	})
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
//	@Autowired
//	private JwtAuthenticationEntryPoint authenticationEntryPoint;
//	
//	@Bean
//	public JwtAuthenticationFilter jwtAuthenticationFilter() {
//		return new JwtAuthenticationFilter();
//	}
	
	//instead of sending plain text we encode the password
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			//.exceptionHandling()
			//.authenticationEntryPoint(authenticationEntryPoint)
			//.and()
			//.sessionManagement()
			//coz we r using jwt
			//.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			//.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/**").permitAll()
			.antMatchers("/api/auth/**").permitAll()
//			.antMatchers("/v3/api-docs/**").permitAll() //to get the json of swagger doc
//			//.antMatchers("/v2/api-docs/**").permitAll() //since we r using swagger doc as Swagger-2
//			.antMatchers("/swagger-ui/**").permitAll()
//			.antMatchers("/swagger-resources/**").permitAll()
//			.antMatchers("/swagger-ui.html/**").permitAll()
//			.antMatchers("/webjars/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
		//http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
////		UserDetails soumya = User.builder().username("soumya").password(passwordEncoder().encode("password")).roles("USER").build();
////		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
////		return new InMemoryUserDetailsManager(soumya, admin);
//		
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//	    manager.createUser(User.withUsername("soumya").password(passwordEncoder().encode("password"))
//	        .roles("USER").build());
//	    manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin"))
//		        .roles("ADMIN").build());
//	    return manager;
//	}
	
	
//	 @Bean
//		public InMemoryUserDetailsManager inMemoryUserDetailsManager()
//		{
//			List<UserDetails> userDetailsList = new ArrayList<>();
//			userDetailsList.add(User.withUsername("soumya").password(passwordEncoder().encode("password"))
//					.roles("USER").build());
//			userDetailsList.add(User.withUsername("admin").password(passwordEncoder().encode("admin"))
//					.roles("ADMIN").build());
//
//			return new InMemoryUserDetailsManager(userDetailsList);
//		}
}
