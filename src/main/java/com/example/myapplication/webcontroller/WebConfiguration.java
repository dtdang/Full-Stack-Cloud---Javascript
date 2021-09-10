// package com.example.myapplication.webcontroller;
// package com.example.myapplication;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


// @Configuration
// public class WebConfiguration extends WebSecurityConfigurerAdapter{
// 	@Override
// 	protected void configure(HttpSecurity http) throws Exception {
// 		http
// 			.authorizeRequests()
// 			.antMatchers("/app/**").authenticated()
// 			.antMatchers("/**").permitAll()
// 			.and()
// 			.oauth2Login() //oauth
// 			.and()
// 			.logout()
// 				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
// 				.logoutSuccessUrl("/");
// 	}


// 	/**
// 	 * This handles the form username and password auth.
// 	 *
// 	 * Username: admin
// 	 * Password: password
// 	 */
// 	@Override
// 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// 		auth .inMemoryAuthentication()
// 			.withUser("admin")
// 			.password("{noop}password")
// 			.roles("ADMIN");
// 	}
// }
