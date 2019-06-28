package com.polaroid.chatweb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.polaroid.chatweb.service.AuthenticatorService;

/**
 * Classe de configuração do Spring. Todas as configurações quanto estruturais
 * do projeto encontram-se aqui
 * 
 * @author Guilherme Melo e Leonardo Braz
 * @version 1.0
 *
 */
@Configuration
@EnableWebSecurity
@EnableWebSocketMessageBroker
@EnableAsync
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements WebSocketMessageBrokerConfigurer {

	private static BCryptPasswordEncoder encoder;

	@Autowired
	private AuthenticatorService authenticator;

	/**
	 * Configuração de rotas e acesso de autenticação
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/register", "/confirm-account").permitAll()
				.antMatchers("/", "/profile/**").authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

	/**
	 * Configuração de provedor de conta de autenticação via banco de dados
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.authenticator).passwordEncoder(encoder());
	}

	/**
	 * Definição de acesso público de conteúdo estático (css e js)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**", "/css/**", "/js/**");
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		if (WebSecurityConfiguration.encoder == null) {
			WebSecurityConfiguration.encoder = new BCryptPasswordEncoder();
		}

		return WebSecurityConfiguration.encoder;
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/user");
		registry.setUserDestinationPrefix("/user");
	}
}
