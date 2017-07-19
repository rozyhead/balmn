package com.github.rozyhead.balmn.configure.security

import com.github.rozyhead.balmn.account.port.adapter.web.PATH_JOIN
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

  override fun configure(http: HttpSecurity) {
    http.authorizeRequests()
        .antMatchers(PATH_JOIN).permitAll()
        .anyRequest().authenticated()
  }

}