package vn.fis.cms.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import vn.fis.cms.services.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
	private AuthenticationProvider customAuthenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin@fis.vn").password("123456").roles("ADMIN");
        auth.authenticationProvider(customAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/account/login").anonymous()
		//.antMatchers("/admin/**").access("hasRole('ADMIN')")
		.and().formLogin().loginPage("/account/login")
		.defaultSuccessUrl("/admin/home").usernameParameter("email").passwordParameter("password")
		.failureUrl("/account/login?error").usernameParameter("email").passwordParameter("password")
        .and().logout().logoutSuccessUrl("/account/login?logout")
        .and().exceptionHandling().accessDeniedPage("/notfound").and().csrf().ignoringAntMatchers("/api/**");
	}

	/*@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}*/
	
	@Bean
	public Md5PasswordEncoder passwordEncoder() throws Exception {
		return new Md5PasswordEncoder();
	}

}