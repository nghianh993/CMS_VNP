package vn.fis.cms.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import vn.fis.cms.services.IActionService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private IActionService actionService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin@fis.vn").password("123456").roles("ADMIN");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/account/login").anonymous()
		.antMatchers("/static/**").permitAll()
		.antMatchers("/notfound").anonymous()
		.anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                FilterInvocationSecurityMetadataSource newSource = new CustomerSecurityMetadataSource(actionService);
                AccessDecisionManager accessDecisionManager = new CustomerAccessDecisionManager();
                fsi.setSecurityMetadataSource(newSource);
                fsi.setAccessDecisionManager(accessDecisionManager);
                return fsi;
            }
        })
		.and().formLogin().loginPage("/account/login")
		.defaultSuccessUrl("/admin/home").usernameParameter("email").passwordParameter("password")
		.failureUrl("/account/login?error").and().logout().logoutSuccessUrl("/account/login?logout")
		.and().exceptionHandling().accessDeniedPage("/notfound")
		.and().csrf().ignoringAntMatchers("/api/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}