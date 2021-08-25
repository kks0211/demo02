package com.board.config;

import com.board.security.CustomLoginSuccessHandler;
import com.board.security.CustomNoOpPasswordEncoder;
import com.board.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Setter(onMethod_ = {@Autowired})
    private final DataSource dataSource;

    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserDetailsService();
    }

    // in custom userdetails
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // log.info("configure JDBC ............................");
    //
    // String queryUser = "select userid , userpw , enabled from tbl_member where
    // userid = ? ";
    // String queryDetails = "select userid, auth from tbl_member_auth where userid
    // = ? ";
    //
    // auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
    // .usersByUsernameQuery(queryUser).authoritiesByUsernameQuery(queryDetails);
    // }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.authorizeRequests()
                .antMatchers("/board/list").permitAll()
                .antMatchers("/board/register")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/board/register")
                .access("hasRole('ROLE_MEMBER')");

        http.formLogin()
                .loginPage("/customLogin")
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler());

        http.logout()
                .logoutUrl("/customLogout")
                .invalidateHttpSession(true)
                .deleteCookies("remember-me", "JSESSION_ID");

        http.rememberMe()
                .key("zerock")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(604800);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*public PasswordEncoder passwordEncoder() {
        return new CustomNoOpPasswordEncoder();
    }*/

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

}
