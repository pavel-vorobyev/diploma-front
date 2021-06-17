package com.pavelvorobyev.diploma.view.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.pavelvorobyev.diploma.R
import com.pavelvorobyev.diploma.util.extensions.disable
import com.pavelvorobyev.diploma.util.extensions.enable
import com.pavelvorobyev.diploma.util.extensions.gone
import com.pavelvorobyev.diploma.util.extensions.visible
import com.pavelvorobyev.diploma.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity : AppCompatActivity(), SignInView {

    private lateinit var presenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        presenter = SignInPresenterImpl(this, applicationContext)

        loginInputView.doOnTextChanged { _, _, _, _ -> errorView.gone() }
        passwordInputView.doOnTextChanged { _, _, _, _ -> errorView.gone() }

        btnSignInView.setOnClickListener {
            presenter.login(
                loginInputView.text.toString().trim(),
                passwordInputView.text.toString().trim()
            )
        }
    }

    override fun showLogins(isLogins: Boolean) {
        if (isLogins) {
            btnSignInView.gone()
            progressView.visible()
            loginInputView.disable()
            passwordInputView.disable()
        } else {
            progressView.gone()
            btnSignInView.visible()
            loginInputView.enable()
            passwordInputView.enable()
        }
    }

    override fun loginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun loginError() {
        errorView.visible()
    }
}
