package com.google.firebase.udacity.friendlychat

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ResultCodes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.udacity.friendlychat.ui.common.NavigationController
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by Nicolas Carrasco S on 7/19/2017.
 */
class SignedOutActivity: AppCompatActivity(){
    @Inject lateinit var auth: FirebaseAuth
    @Inject lateinit var authUI: AuthUI
    val navigationController by lazy { NavigationController(this, authUI) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        if(auth.currentUser != null){
            navigationController.navigateToSignedIn()
        } else {
            navigationController.navigateToSignIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            NavigationController.RC_SIGN_IN -> {
                if (resultCode == ResultCodes.OK) navigationController.navigateToSignedIn()
                else finish()
            }
            else ->{}
        }
    }
}