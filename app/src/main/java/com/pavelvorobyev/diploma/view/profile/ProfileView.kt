package com.pavelvorobyev.diploma.view.profile

interface ProfileView {
    fun setData(id: String, name: String)
    fun loggedOut()
}
