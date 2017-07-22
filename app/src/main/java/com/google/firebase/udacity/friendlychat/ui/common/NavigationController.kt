package com.google.firebase.udacity.friendlychat.ui.common

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.udacity.friendlychat.SignedInActivity
import com.google.firebase.udacity.friendlychat.SignedOutActivity

/**
 * Created by Nicolas Carrasco S on 7/19/2017.
 */
class NavigationController(val activity: AppCompatActivity,
                           val authUI: AuthUI) {

    companion object {
        val RC_SIGN_IN = 10
        val RC_PHOTO_PICKER = 20
    }

    fun navigateToSignIn() {
        activity.startActivityForResult(
                authUI
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(
                                listOf(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                        .build(),
                RC_SIGN_IN)
    }

    fun navigateToSignOut() {
        authUI.signOut(activity).addOnCompleteListener { navigateToSignedOut() }
    }

    fun navigateToSignedOut() {
        activity.startActivity(Intent(activity, SignedOutActivity::class.java))
        activity.finish()
    }

    fun navigateToSignedIn() {
        activity.startActivity(Intent(activity, SignedInActivity::class.java))
        activity.finish()
    }

    fun navigateToPhotoPicker() {
        with(activity) {
            startActivityForResult(
                    Intent(Intent.ACTION_GET_CONTENT)
                            .setType("image/jpg")
                            .putExtra(Intent.EXTRA_LOCAL_ONLY, true),
                    RC_PHOTO_PICKER)
        }
    }
}