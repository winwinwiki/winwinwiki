package org.winwin.config;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

//@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig {

	// extends WebSecurityConfigurerAdapter

//	@Autowired
//	UserDetailsService userDetailsService;

	
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		log.error("configureGlobal");
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}
//
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		log.error("configure HttpSecurity");
//		 http
//	        .sessionManagement()
//	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		 
////		log.error("Configure called");
////		http.httpBasic().and().authorizeRequests()
////		.antMatchers("/students/**").hasRole("admin")
////		.antMatchers("/**").hasRole("admin")
////		.and().csrf().disable().headers().frameOptions().disable();
//	}
}