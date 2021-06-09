package com.pavelvorobyev.diploma.view.signin

import android.content.Context
import com.pavelvorobyev.diploma.businesslogic.repository.GuardRepository
import com.pavelvorobyev.diploma.businesslogic.store.Store
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class SignInPresenterImpl(
    private val view: SignInView,
    context: Context
) : SignInPresenter {

    private val store = Store(context)
    private val guardRepository = GuardRepository(context)

    override fun login(login: String, password: String) {
        guardRepository.login(login, password)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLogins(true) }
            .doFinally { view.showLogins(false) }
            .subscribe(
                { response ->
                    val token = response?.token.orEmpty()
                    val guard = response?.guard
                    store.storeToken(token)
                    store.storeUser(guard?.id.orEmpty(), guard?.name.orEmpty())
                    view.loginSuccess()
                },
                {
                    view.loginError()
                }
            )
    }
}

interface SignInPresenter {
    fun login(login: String, password: String)
}

