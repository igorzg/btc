package org.cryptobroker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@PropertySource("classpath:config.properties")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

  @Value("${global.cors.domain}")
  private String corsDomain;

  @Value("${global.cors.methods}")
  private String corsMethods;

  @Value("${global.cors.headers}")
  private String corsHeaders;

  @Value("${global.cors.expose-headers}")
  private String corsExposeHeaders;

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList(corsDomain.split(",")));
    configuration.setAllowedMethods(Arrays.asList(corsMethods.split(",")));
    configuration.setAllowedHeaders(Arrays.asList(corsHeaders.split(",")));
    configuration.setExposedHeaders(Arrays.asList(corsExposeHeaders.split(",")));
    configuration.setAllowCredentials(false);
    configuration.setMaxAge(3600L);

    logger.debug("Allowed origins {}", configuration.getAllowedOrigins());
    logger.debug("Allowed methods {}", configuration.getAllowedMethods());
    logger.debug("Allowed headers {}", configuration.getAllowedHeaders());

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin().disable();
    http.csrf().disable();
    http.logout().disable();
    http.cors().configurationSource(corsConfigurationSource());
  }

}
