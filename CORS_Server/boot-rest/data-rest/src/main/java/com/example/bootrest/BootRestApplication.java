package com.example.bootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class BootRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootRestApplication.class, args);
    }

    //CORS구현
    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            CorsConfiguration configuration=new CorsConfiguration();
            configuration.addAllowedOrigin(CorsConfiguration.ALL);
            configuration.addAllowedMethod(CorsConfiguration.ALL);
            configuration.addAllowedHeader(CorsConfiguration.ALL);

            //특정 경로에 대해서 CorsConfiguration에서 설정한 값을
            //CorsConfigurationSource인터페이스를 구현한 UrlBasedCorsConfigurationSource에 적용
            UrlBasedCorsConfigurationSource source=
                    new UrlBasedCorsConfigurationSource();

            source.registerCorsConfiguration("/**",configuration);//모든 경로로 설정

            http.httpBasic()
                    .and().authorizeRequests()
                    .anyRequest().permitAll()
                    .and().cors().configurationSource(source)
                    .and().csrf().disable();
        }
    }

}
