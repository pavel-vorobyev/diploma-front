package com.pavelvorobyev.diploma.view.profile

import android.content.Context
import com.pavelvorobyev.diploma.businesslogic.store.Store

class ProfilePresenterImpl(
    private val view: ProfileView,
    context: Context
) : ProfilePresenter {

    private val store = Store(context)

    override fun getData() {
        view.setData(store.id, store.name)
    }

    override fun logOut() {
        store.clearAll()
        view.loggedOut()
    }
}

interface ProfilePresenter {
    fun getData()
    fun logOut()
}
