package com.pavelvorobyev.diploma.view.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pavelvorobyev.diploma.R
import com.pavelvorobyev.diploma.view.signin.SignInActivity
import kotlinx.android.synthetic.main.fragment_profile.btnLogOutView
import kotlinx.android.synthetic.main.fragment_profile.idView
import kotlinx.android.synthetic.main.fragment_profile.nameView

class ProfileFragment : Fragment(), ProfileView {

    private lateinit var presenter: ProfilePresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = ProfilePresenterImpl(this, context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getData()

        btnLogOutView.setOnClickListener {
            presenter.logOut()
        }
    }

    override fun setData(id: String, name: String) {
        idView.text = id
        nameView.text = name
    }

    override fun loggedOut() {
        val intent = Intent(context, SignInActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
