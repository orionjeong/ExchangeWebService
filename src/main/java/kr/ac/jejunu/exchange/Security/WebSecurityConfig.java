package kr.ac.jejunu.exchange.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    AuthProvider authProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/css/**", "/js/**","/image/**", "/static/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/**").permitAll();

        //일단 막아놓자
        http.csrf().disable();

        //restful api로그인을 위한 form로그인 막기
//        http.formLogin()
//                .and()
                http.logout();



//        http.sessionManagement().
//                sessionFixation().
//                migrateSession().
////                invalidSessionUrl("/").
//        maximumSessions(1).
//                maxSessionsPreventsLogin(false).
//
//                expiredUrl("/login?duplicate");
//

//        http.formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error")
//                .defaultSuccessUrl("/loginSuccess")
//                .usernameParameter("uid")
//                .passwordParameter("password");
//
//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .deleteCookies("JSESSIONID", "uid")
//                .invalidateHttpSession(true);

        http.authenticationProvider(authProvider);




    }

    @Bean
    @Override public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }




}
