/**
 * Copyright 2019 Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Objects;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Order(1)
public class AuthConfiguration {
    private static final String API_KEY_AUTH_HEADER_NAME = "API_KEY";
    @Value("${app.http.auth-token-header-name}")
    private String principalRequestHeader;

    @Value("${app.http.auth-token}")
    private String principalRequestValue;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      ApiKeyAuthFilter filter = new ApiKeyAuthFilter(principalRequestHeader);
      RequestMatcher apiRequestMatcher = new RegexRequestMatcher("^/demoApp/.*", null);

      filter.setAuthenticationManager(
          authentication -> {
            String principal = (String) authentication.getPrincipal();
            System.out.println("principal"+principal);
            if (!Objects.equals(principalRequestValue, principal)) {
              throw new BadCredentialsException(
                  "The API key was not found or not the expected value.");
            }
            authentication.setAuthenticated(true);
            return authentication;
          });
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(apiRequestMatcher).authenticated()
                        )
                .csrf(withDefaults())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(filter)
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().authenticated());
      return http.build();
    }
  }
