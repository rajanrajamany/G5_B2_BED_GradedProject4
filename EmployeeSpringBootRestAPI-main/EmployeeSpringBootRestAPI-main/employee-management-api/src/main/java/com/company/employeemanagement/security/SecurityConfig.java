package com.company.employeemanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.company.employeemanagement.service.MyUserDetailsService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.myUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/users/**", "/api/roles/**").hasAnyAuthority("ADMIN", "USER")
		.antMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/**").hasAnyAuthority("ADMIN")
		.antMatchers(HttpMethod.GET,"/api/employees", "/api/employees/").hasAnyAuthority("ADMIN", "USER")
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.formLogin();
	}


}