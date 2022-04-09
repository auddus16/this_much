package com.project.thismuch.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/* .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint) :

- exceptionHandling을 위해서 실제 구현한 jwtAuthenticationEntryPoint을 넣어준다 jwtAuthenticationEntryPoint 아래에서 구현한다.

.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

- Spring Security에서 Session을 생성하거나 사용하지 않도록 설정한다.

http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

- jwtRequestFilter역시 아래에서 구현한다
*/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

//	@Autowired
//	private UserDetailsService userDetailsService;

//	@Autowired
//	private JwtRequestFilter jwtRequestFilter;

//	@Autowired
//	private DataSource dataSource;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.jdbcAuthentication().dataSource(dataSource);
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/user/login", "/api/member").permitAll()
				.antMatchers("/swagger-resources/**", "/", "/8f1cc972-1521-46e5-8453-ff9edc5b33d2").permitAll() // swagger
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
