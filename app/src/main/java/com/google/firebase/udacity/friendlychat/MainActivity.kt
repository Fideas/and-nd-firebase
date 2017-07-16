package com.google.firebase.udacity.friendlychat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Nicolas Carrasco S on 7/16/2017.
 */
class MainActivity : AppCompatActivity() {

    val ANONYMOUS = "anonymous"
    val DEFAULT_MSG_LENGTH_LIMIT = 1000
    private val userName by lazy { ANONYMOUS }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val friendlyMessages = ArrayList<FriendlyMessage>()
        val messageAdapter = MessageAdapter(this, R.layout.item_message, friendlyMessages)
        messageListView.adapter = messageAdapter

        progressBar.visibility = View.INVISIBLE

        photoPickerButton.setOnClickListener {
            // TODO: Fire an intent to show an image picker
        }

        messageEditText.onTextChangedListener {
            s, _, _, _ -> sendButton.isEnabled = s?.isNotEmpty() ?: false  }

        messageEditText.filters = arrayOf(InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT))

        sendButton.setOnClickListener {
            // TODO: Send messages on click
            messageEditText.setText("")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }
}