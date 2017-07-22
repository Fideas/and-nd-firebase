package com.google.firebase.udacity.friendlychat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import com.google.firebase.udacity.friendlychat.ui.common.NavigationController
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Nicolas Carrasco S on 7/16/2017.
 */
class SignedInActivity : AppCompatActivity() {

    val ANONYMOUS = "anonymous"
    val DEFAULT_MSG_LENGTH_LIMIT = 1000
    private val userName by lazy { auth.currentUser?.displayName ?: ANONYMOUS }

    @field:[Inject Named("messages")] lateinit var messageDatabaseReference: DatabaseReference
    @field:[Inject Named("chat_photos")] lateinit var chatPhotoStorageReference: StorageReference
    @Inject lateinit var auth: FirebaseAuth
    @Inject lateinit var authUI: AuthUI
    val navigationController by lazy { NavigationController(this, authUI) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        if (auth.currentUser == null) {
            navigationController.navigateToSignedOut()
            return
        }
        setContentView(R.layout.activity_main)

        val friendlyMessages = ArrayList<FriendlyMessage>()
        val messageAdapter = MessageAdapter(this, R.layout.item_message, friendlyMessages)
        messageListView.adapter = messageAdapter

        progressBar.visibility = View.INVISIBLE

        photoPickerButton.setOnClickListener {
            navigationController.navigateToPhotoPicker()
        }

        messageEditText.onTextChangedListener {
            s, _, _, _ ->
            sendButton.isEnabled = s?.isNotEmpty() ?: false
        }

        messageEditText.filters = arrayOf(InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT))

        sendButton.setOnClickListener {
            val message = FriendlyMessage(messageEditText.text.toString(), userName, null)
            messageDatabaseReference.push().setValue(message)
            messageEditText.setText("")
        }
        messageDatabaseReference.addChildEventListener(MessageEventListener(messageAdapter))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sign_out_menu -> navigationController.navigateToSignOut()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            NavigationController.RC_PHOTO_PICKER -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val selectedUri = data?.data
                        val photoRef = chatPhotoStorageReference.child(selectedUri?.lastPathSegment!!)

                        photoRef.putFile(selectedUri)
                                .addOnSuccessListener { it ->
                                    messageDatabaseReference
                                            .push()
                                            .setValue(FriendlyMessage(
                                                    null,
                                                    userName,
                                                    it.downloadUrl.toString()
                                            ))
                                }

                    }
                }
            }
        }
    }
}