package kr.ac.jejunu.exchange.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;







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
        http.csrf().disable().exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).
        and().authorizeRequests()
                .antMatchers("/view/addproduct").authenticated()
                .antMatchers("/view/addexchange").authenticated()
                .antMatchers("/view/productList").authenticated()
                .antMatchers("/view/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/product").access("hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.DELETE, "/api/**/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/product/**").permitAll()
                .antMatchers("/login").permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/view/index");;

        http.authenticationProvider(authProvider);
    }

    @Bean
    @Override public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }



}
