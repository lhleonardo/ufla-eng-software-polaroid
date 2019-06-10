package com.polaroid.chatweb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.polaroid.chatweb.service.AuthenticatorService;

@Configuration
@EnableWebSecurity
@EnableAsync
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AuthenticatorService authenticator;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/register", "/confirm-account").permitAll()
			.antMatchers("/").authenticated()
				.and()
					.formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/")
						.permitAll()
				.and()
					.logout()
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(this.authenticator)
			.passwordEncoder(encoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/static/**", "/css/**", "/js/**");
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
