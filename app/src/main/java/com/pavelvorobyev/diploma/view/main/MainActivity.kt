package com.pavelvorobyev.diploma.view.main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.pavelvorobyev.diploma.R
import com.pavelvorobyev.diploma.view.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_main.bottomNavView

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenterImpl(this, applicationContext)
        presenter.checkSession()

        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.navHostFragment)
        bottomNavView.setupWithNavController(navController)
    }

    override fun loggedOut() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}
