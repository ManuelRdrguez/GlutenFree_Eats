package com.example.glutenfree.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.glutenfree.entities.Rol;
import com.example.glutenfree.entities.Usuario;
import com.example.glutenfree.repository.UserRepository;
import com.example.glutenfree.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private UserService usuarioServicio;
	   @Autowired
	    private UserRepository userRepository;
	    @Autowired
	    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	public SecurityConfiguration(UserService userService) {
		this.usuarioServicio = userService;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/registro", "/login", "/css/**", "/images/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN") // Solo los administradores pueden acceder a las URLs que empiezan con /admin/

						.anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").permitAll()
                .successHandler(customAuthenticationSuccessHandler))
				.logout((logout) -> logout.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
						.permitAll());
		return http.build();
	}

}
