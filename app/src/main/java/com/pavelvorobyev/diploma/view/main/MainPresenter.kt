package com.pavelvorobyev.diploma.view.main

import android.content.Context
import com.pavelvorobyev.diploma.businesslogic.store.Store
import com.pavelvorobyev.diploma.extensions.Empty

class MainPresenterImpl(
    private val view: MainView,
    context: Context
) : MainPresenter {

    private val store = Store(context)

    override fun checkSession() {
        if (store.token == String.Empty) {
            view.loggedOut()
        }
    }
}

interface MainPresenter {
    fun checkSession()
}
