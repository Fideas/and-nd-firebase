package com.google.firebase.udacity.friendlychat

import android.app.Activity
import android.widget.Toast

/**
 * Created by Nicolas Carrasco S on 7/19/2017.
 */
fun Activity.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}