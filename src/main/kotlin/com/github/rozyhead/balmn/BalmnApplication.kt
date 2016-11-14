package com.github.rozyhead.balmn

import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@SpringBootApplication
@EnableTransactionManagement
open class BalmnApplication {

  @Bean
  open fun transactionManager(dataSource: DataSource) = SpringTransactionManager(dataSource)

//  @Bean
//  open fun persistenceExceptionTranslationPostProcessor() = PersistenceExceptionTranslationPostProcessor()

}

fun main(args: Array<String>) {
  SpringApplication.run(BalmnApplication::class.java, *args)
}

