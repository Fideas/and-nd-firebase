package com.google.firebase.udacity.friendlychat

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by Nicolas Carrasco S on 7/16/2017.
 */
fun EditText.onTextChangedListener(listener: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            listener(s, start, before, count)
        }

        override fun afterTextChanged(s: Editable?) {

        }
    })
}