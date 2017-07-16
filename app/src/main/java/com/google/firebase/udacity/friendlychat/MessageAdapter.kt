package com.google.firebase.udacity.friendlychat

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_message.view.*

/**
 * Created by Nicolas Carrasco S on 7/16/2017.
 */
class MessageAdapter(
        context: Context,
        resource: Int,
        objects: List<FriendlyMessage>) : ArrayAdapter<FriendlyMessage>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?:
                (context as Activity).layoutInflater.inflate(R.layout.item_message, parent, false)

        val message = getItem(position)
        with(view) {
            if (message.photoUrl != null) {

                messageTextView.visibility = View.GONE
                photoImageView.visibility = View.VISIBLE
                Picasso.with(context)
                        .load(message.photoUrl)
                        .into(photoImageView)
            } else {

                messageTextView.visibility = View.VISIBLE
                photoImageView.visibility = View.GONE
                messageTextView.text = message.text

            }
            nameTextView.text = message.name
        }
        return view
    }
}