package com.pavelvorobyev.diploma.view.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.pavelvorobyev.diploma.R
import com.pavelvorobyev.diploma.util.extensions.gone
import com.pavelvorobyev.diploma.util.extensions.visible
import com.pavelvorobyev.diploma.view.signin.SignInActivity
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.android.synthetic.main.activity_main.bottomNavView
import kotlinx.android.synthetic.main.activity_main.progressBarView
import pl.aprilapps.easyphotopicker.*


@SuppressLint("ClickableViewAccessibility")
class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter
    private val permissions = RxPermissions(this)
    private lateinit var imagePicker: EasyImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenterImpl(this, applicationContext)
        presenter.checkSession()

        imagePicker = EasyImage.Builder(this)
            .allowMultiple(false)
            .setChooserType(ChooserType.CAMERA_AND_GALLERY)
            .build()

        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.navHostFragment)
        bottomNavView.setupWithNavController(navController)
        bottomNavView.setItemOnTouchListener(R.id.scanView) { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                openScanner()
            }
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imagePicker.handleActivityResult(requestCode, resultCode, data, this,
            object : DefaultCallback() {
                override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                    presenter.verifyVisitor(imageFiles)
                }

                override fun onImagePickerError(error: Throwable, source: MediaSource) {
                    super.onImagePickerError(error, source)
                    Toast.makeText(this@MainActivity, "Unknown error occurred", Toast.LENGTH_LONG)
                        .show()
                }
            })
    }

    private fun openScanner() {
        permissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe { granted ->
                if (granted) {
                    imagePicker.openChooser(this)
                } else {
                    Toast.makeText(this, "Permissions wasn't granted", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun loggedOut() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showProgress(isShow: Boolean) {
        if (isShow) {
            progressBarView.visible()
        } else {
            progressBarView.gone()
        }
    }
}
