package dk.webshopmodule.config;

import dk.webshopmodule.web.LoggingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers( "/admin/updateProduct");
        web.ignoring().antMatchers( "/admin/createProduct");
        web.ignoring().antMatchers( "/admin/createDeliveryPost");
        web.ignoring().antMatchers( "/admin/updateDeliveryPost");
        web.ignoring().antMatchers( "/admin/createPaymentPost");
        web.ignoring().antMatchers( "/admin/updatePaymentPost");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**",
                        "/fragments/**",
                        "/*").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/redirect")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // password = $2a$10$GkHRhh4AHWS.WHUzRucUIeBoEmowH7qZ2HLVas544VbXFscstpEE6
        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT username, role FROM users INNER JOIN roles ON users.role_fk = roles.r_id WHERE username=?")
                .usersByUsernameQuery("SELECT username,password as password,1 FROM users WHERE username=?")
                .passwordEncoder(passEncoder());
    }

    @Bean
    public PasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder();
    }
}