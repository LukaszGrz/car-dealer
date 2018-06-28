package pl.sdacademy.spring.car_dealer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                // niech istnieje użytkownik o nazwe "Ecik", majacy haslo "Ecik123" oraz role o nazwie "VEHICLES"
                .withUser("Ecik").password("Ecik123").roles("VEHICLES").and()
                // niech istnieje użytkownik o nazwe "Adelajda", majacy haslo "Adelajda123" oraz role o nazwie "SALES"
                .withUser("Adelajda").password("Adelajda123").roles("SALES");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // niech adres "/" będzie dostepny dla kazdego
                .antMatchers("/").permitAll()
                // niech adres "/hi" będzie dostepny dla kazdego
                .antMatchers("/hi").permitAll()
                // niech adres "/vehicles" będzie dostepny dla kazdego
                .antMatchers("/vehicles").permitAll()
                // niech adres "/login" będzie dostepny tylko dla uzytkownikow, którzy nie sa jeszcze zalogowani
                .antMatchers("/login").anonymous()
                // niech wszystkie podstrony "/vehicles" będzie dostepny tylko dla uzytkownikow, którzy posiadaja role VEHICLES
                .antMatchers("/vehicles/*").hasRole("VEHICLES")
                .antMatchers("/purchases*").hasRole("SALES")
                .antMatchers("/vehicles/*").hasRole("SALES")
                // dowolny inny request bedzie obsluzony, jezeli uzytkownik posiada jedna z rol VEHICLES lub SALES
                .anyRequest().hasAnyRole("VEHICLES", "SALES")
                .and()
                // formularz logowania bedzie znajdowal sie pod adresem "login". Po zalogowaniu, uzytkownik bedzie przekierowany
                // na adres "/vehicles". Wazne! na adres /login musi byc wyslany POST!
                .formLogin().loginPage("/login").defaultSuccessUrl("/vehicles")
                // wylogowanie przekieruje na strone vehicles.
                .and().logout().logoutSuccessUrl("/vehicles");
    }
}
