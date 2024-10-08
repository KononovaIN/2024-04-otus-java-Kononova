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
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;


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
                //.and()
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/journal").authenticated()
                .antMatchers("/clients").authenticated()
                .antMatchers("/books").authenticated()
                .antMatchers("/bookTypes").authenticated()
                .antMatchers("/library/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }
}
