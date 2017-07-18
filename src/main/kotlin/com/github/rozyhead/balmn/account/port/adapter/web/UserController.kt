package com.github.rozyhead.balmn.account.port.adapter.web

import com.github.rozyhead.balmn.account.application.RegisterUserAccountUsecase
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.authentication.password.PlainPassword
import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.ScriptAssert
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class UserController(
    private val registerUserAccountUsecase: RegisterUserAccountUsecase
) {

  @ScriptAssert(lang = "javascript", script = "_this.password === _this.passwordConfirmation")
  data class RegisterUserAccountForm(
      @get:NotBlank
      var accountName: String = "",
      @get:NotBlank
      var password: String = "",
      @get:NotBlank
      var passwordConfirmation: String = ""
  )

  val log = LoggerFactory.getLogger(UserController::class.java)

  @GetMapping("/accounts/_newUser")
  fun showRegisterUserAccountForm(model: Model): String {
    model.addAttribute(RegisterUserAccountForm())
    return "accounts/newUser"
  }

  @PostMapping("/accounts/_newUser")
  fun registerUserAccount(@Valid registerUserAccountForm: RegisterUserAccountForm, bindingResult: BindingResult): String {
    log.info("{}", registerUserAccountForm)
    if (bindingResult.hasErrors()) {
      return "accounts/newUser"
    }

    val (accountName, password) = registerUserAccountForm
    val command = RegisterUserAccountUsecase.RegisterUserAccountCommand(AccountName(accountName), PlainPassword(password))
    registerUserAccountUsecase.execute(command)

    return "accounts/newUser_success"
  }

}