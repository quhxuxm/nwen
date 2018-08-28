package online.nwen.entry;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
@EnableGlobalAuthentication
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    public SecurityConfigure(
            @Qualifier("UserDetailsServiceImpl")
                    UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/article/create", "/api/article/update",
                        "/api/article/praise", "/api/article/bookmark",
                        "/api/article/comment/create", "/api/anthology/create",
                        "/api/anthology/update", "/api/anthology/bookmark",
                        "/api/anthology/praise", "/api/anthology/comment/create",
                        "/api/author/update", "/api/author/follow",
                        "/api/author/initialize")
                .authenticated()
                .anyRequest().permitAll();
        http.formLogin().loginPage("/api/author/authenticate")
                .successForwardUrl("/api/author/initialize");
        http.logout().logoutUrl("/api/author/logout")
                .logoutSuccessUrl("/api/author/clear");
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }
}