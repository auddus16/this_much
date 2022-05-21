package com.project.thismuch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/mw/**", "/my/**", "/api/member").permitAll()
				.antMatchers("/swagger-resources/**", "/thismuch/**").permitAll() // swagger
				.antMatchers("/api/moreInfo/**", "/api/oauth/**", "/api/category/**").permitAll()
				.anyRequest().authenticated().and()
				.csrf().disable()
				.formLogin();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",

				"/swagger-resources", "/configuration/security",

				"/swagger-ui/**", "/webjars/**", "/swagger/**", "configuration/**");

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
