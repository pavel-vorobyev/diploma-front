package com.pavelvorobyev.diploma.view.signin

import android.content.Context

interface SignInView {
    fun showLogins(isLogins: Boolean)
    fun loginSuccess()
    fun loginError()
}
