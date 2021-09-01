package com.board.security.custom;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class BrmsWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http.authorizeRequests()
                .antMatchers("/login**", "/web-resources/**", "/actuator/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/order/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .addFilterBefore(customAuthenticationProcessingFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    // 커스텀 인증 필터
    @Bean
    public CustomAuthenticationProcessingFilter customAuthenticationProcessingFilter() {
        CustomAuthenticationProcessingFilter filter = new CustomAuthenticationProcessingFilter("/login-process");
        filter.setAuthenticationManager(customAuthenticationManager());
        //filter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler("/login"));
        filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
        return filter;
    }

    // 커스텀 인증 매니저
    @Bean
    public CustomAuthenticationManager customAuthenticationManager() {
        return new CustomAuthenticationManager();
    }

}
