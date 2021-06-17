package com.pavelvorobyev.diploma.businesslogic.store

import android.content.Context
import com.pavelvorobyev.diploma.util.extensions.Empty

class Store(context: Context) {

    private val store = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    private val editor = store.edit()

    val token: String
        get() = store.getString("token", String.Empty) ?: String.Empty

    val id: String
        get() = store.getString("id", String.Empty) ?: String.Empty

    val name: String
        get() = store.getString("name", String.Empty) ?: String.Empty

    fun storeToken(token: String) {
        editor.putString("token", token)
        editor.commit()
    }

    fun storeUser(id: String, name: String) {
        editor.putString("id", id)
        editor.putString("name", name)
        editor.commit()
    }

    fun logOut() {
        editor.clear()
        editor.commit()
    }
}
