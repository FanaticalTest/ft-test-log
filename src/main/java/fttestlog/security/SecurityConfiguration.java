package fttestlog.security;

import fttestlog.lib.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import fttestlog.security.CustomBasicAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static String REALM="MY_TEST_REALM";
  private Property prop = new Property();
  private final String FT_ADMIN_USERNAME = prop.read("ft_admin_username");
  private final String FT_ADMIN_PASSWORD = prop.read("ft_admin_password");
  private final String FT_USER_USERNAME = prop.read("ft_user_username");
  private final String FT_USER_PASSWORD = prop.read("ft_user_password");

  @Autowired
  public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser(FT_ADMIN_USERNAME).password(FT_ADMIN_PASSWORD).roles("ADMIN");
    auth.inMemoryAuthentication().withUser(FT_USER_USERNAME).password(FT_USER_PASSWORD).roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/**/add").hasRole("ADMIN")
        .antMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
  }

  @Bean
  public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
    return new CustomBasicAuthenticationEntryPoint();
  }

  /* To allow Pre-flight [OPTIONS] request from browser */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
  }
}