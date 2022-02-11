package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.AutenticacaoService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    //autenticacao
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    //autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()
                .antMatchers("/api/v1/fresh-products/").hasAnyAuthority("Comprador", "Vendedor", "Representante")
                .antMatchers("/api/v1/fresh-products/list").hasAnyAuthority("Comprador", "Vendedor", "Representante")
                .antMatchers("/api/v1/fresh-products/list/anuncio/*").hasAnyAuthority("Representante")
                .antMatchers("/api/v1/fresh-products/list/anuncio").hasAnyAuthority("Representante")
                .antMatchers("/api/v1/fresh-products/orders").hasAnyAuthority("Comprador")
                .antMatchers("/api/v1/fresh-products/orders/*").hasAnyAuthority("Comprador")
                .antMatchers("/api/v1/fresh-products/inboundorder").hasAnyAuthority("Representante")
                .antMatchers("/api/v1/fresh-products/inboundorder/*").hasAnyAuthority("Representante")
                .antMatchers(HttpMethod.GET, "/api/v1/fresh-products/capacidadeSetor/*").hasAnyAuthority("Representante")
                .antMatchers(HttpMethod.GET,"/api/v1/fresh-products/warehouse/").hasAnyAuthority("Representante")
                .antMatchers(HttpMethod.GET,"/api/v1/fresh-products/warehouse/*").hasAnyAuthority("Representante")
                .antMatchers("/api/v1/fresh-products/due-date").hasAnyAuthority("Representante")
                .antMatchers("/api/v1/fresh-products/due-date/list").hasAnyAuthority("Representante")
                .antMatchers("/api/v1/stock-report").hasAuthority("Representante")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, repository), UsernamePasswordAuthenticationFilter.class);
    }
    //autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(autenticacaoService).passwordEncoder(encoder);
    }
}
