package com.github.rozyhead.balmn.configure.web

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class WebMvcConfiguration(
    private val messageSource: MessageSource
) : WebMvcConfigurerAdapter() {

  @Bean
  fun validator(): LocalValidatorFactoryBean = LocalValidatorFactoryBean().apply {
    setValidationMessageSource(messageSource)
  }

  override fun getValidator(): Validator {
    return validator()
  }

}