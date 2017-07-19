package com.github.rozyhead.balmn.account.port.adapter.web

import com.github.rozyhead.balmn.account.application.RegisterUserAccountUsecase
import com.github.rozyhead.balmn.account.application.RegisterUserAccountUsecase.RegisterUserAccountCommand
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.AccountOperationException
import com.github.rozyhead.balmn.account.domain.model.authentication.password.PlainPassword
import org.hibernate.validator.constraints.NotBlank
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid
import javax.validation.constraints.AssertTrue

const val PATH_JOIN = "/join"

@Controller
class JoinController(
    private val registerUserAccountUsecase: RegisterUserAccountUsecase
) {

  data class JoinForm(
      @field:NotBlank
      var accountName: String = "",
      @field:NotBlank
      var password: String = "",
      @field:NotBlank
      var passwordConfirmation: String = ""
  ) {
    val passwordConfirmationMatches: Boolean
      @AssertTrue(message = "{passwordConfirmationMatches}")
      get() {
        return password.isBlank() || passwordConfirmation.isBlank()
            || password == passwordConfirmation
      }
  }

  private val log = LoggerFactory.getLogger(JoinController::class.java)

  @GetMapping(PATH_JOIN)
  fun getForm(model: Model): String {
    model.addAttribute(JoinForm())
    return "join"
  }

  @PostMapping(PATH_JOIN)
  fun postForm(@Valid joinForm: JoinForm, bindingResult: BindingResult): ModelAndView {
    if (bindingResult.hasErrors()) {
      return ModelAndView(
          "join",
          bindingResult.model,
          HttpStatus.BAD_REQUEST
      )
    }

    val (accountName, password) = joinForm
    val command = RegisterUserAccountCommand(AccountName(accountName), PlainPassword(password))
    return try {
      registerUserAccountUsecase.execute(command)
      ModelAndView("join")
    } catch (e: AccountOperationException.AccountNameAlreadyUsedException) {
      bindingResult.rejectValue("accountName", "accountNameAlreadyUsed", e.javaClass.name)
      ModelAndView(
          "join",
          bindingResult.model,
          HttpStatus.BAD_REQUEST
      )
    }
  }

}