package com.judge_server.web.configuration;

import com.judge_server.core.entity.user.Role;
import com.judge_server.web.service.SignInUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SignInUserDetailsService signInUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/fonts/**", "/js/**").permitAll()
                .antMatchers("/", "/signUp").permitAll()
                .antMatchers("/problem/create").hasRole(Role.Admin.toString())
                .anyRequest().hasRole(Role.User.toString())
                .and()
            .formLogin()
                .loginPage("/signIn")
                .loginProcessingUrl("/signIn")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(signInUserDetailsService).passwordEncoder(passwordEncoder);
    }

}
