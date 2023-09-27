package lt.viltiesziedas.filmai.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/*").authenticated()
                .antMatchers("/kategorija/pridejimas").hasAnyRole("ADMIN")
                .antMatchers("/kategorija/redagavimas/*").hasAnyRole("ADMIN")
                .antMatchers("/kategorija/istrinti/*").hasAnyRole("ADMIN")
                .antMatchers("/filmas/pridejimas").hasAnyRole("ADMIN")
                .antMatchers("filmai/redagavimas/*").hasAnyRole("ADMIN")
                .antMatchers("/filmai/pridetas").hasAnyRole("ADMIN")
                .antMatchers("/filmai/istrinti/*").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout();
        return http.build();
    }

}
