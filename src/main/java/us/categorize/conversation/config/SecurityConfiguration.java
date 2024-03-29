package us.categorize.conversation.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import us.categorize.conversation.service.SlightlySocialUserDetailService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
            jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(
                    "select username,password, enabled from users where username=?")
            .authoritiesByUsernameQuery(
                    "select username, authority from authorities where username=?");
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        http
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login/authenticate")
            .failureUrl("/login?param.error=bad_credentials")
            .permitAll()
    .and()
        .logout()
            .logoutUrl("/logout")
            .deleteCookies("JSESSIONID")
    .and()
        .authorizeRequests()
            .antMatchers("/favicon.ico", "/static/**", "/greeting", "/static-resources/**", "/css/**").permitAll()
            .antMatchers("/**").authenticated()
    .and()
        .rememberMe()
    .and()
        .apply(new SpringSocialConfigurer()
            .postLoginUrl("/")
            .alwaysUsePostLoginUrl(true));
    }
    
    @Bean
    public SocialUserDetailsService socialUsersDetailService() {
        return new SlightlySocialUserDetailService(userDetailsService());
    }
}
