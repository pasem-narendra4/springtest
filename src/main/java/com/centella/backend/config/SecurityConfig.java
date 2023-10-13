package com.centella.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.centella.backend.filter.JwtAuthFilter;
import com.centella.backend.util.JwtUtils;

@CrossOrigin
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)	
public class SecurityConfig{
//	
	
	@Autowired
	private JwtUtils jwtUtils;
	
//	@Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(requests ->{
//					try {
//						requests
//						.requestMatchers("/login").permitAll()
//						.anyRequest().authenticated()
//						.and()
//						.formLogin(login -> login
//						        .loginPage("/login")
//						        .defaultSuccessUrl("/"))
//						.logout(logout -> logout
//						        .logoutUrl("/logout")
//						        .logoutSuccessUrl("/login")
//						        .invalidateHttpSession(true));
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				});
//
//        return http.build();
//    }
	
	@Bean
	public RoleHierarchy roleHierarchy() {
	    RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
	    hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
	    return hierarchy;
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf().disable()
	        .authorizeRequests(requests -> requests
	            .requestMatchers("/login").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .addFilterBefore(new JwtAuthFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class));

	    return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
	
	
}
