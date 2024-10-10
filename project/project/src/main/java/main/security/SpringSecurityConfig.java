package main.security;

import main.security.jwt.JwtSecurityConfigurer;
import main.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin(form -> form.defaultSuccessUrl("/books", true))
        .authorizeRequests()
        .antMatchers("/registration").permitAll()
        .antMatchers("/journal").hasRole("ADMIN")
        .antMatchers("/clients").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/books").authenticated()
        .antMatchers(HttpMethod.GET, "/bookTypes").authenticated()
        .antMatchers(HttpMethod.GET, "/library/**").authenticated()
        .antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/bookTypes").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/library/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/books").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/bookTypes").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/library/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/books").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/bookTypes").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/library/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        .httpBasic().disable()
        .apply(new JwtSecurityConfigurer(jwtTokenProvider));
  }
}