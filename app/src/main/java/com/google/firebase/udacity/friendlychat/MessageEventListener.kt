package com.google.firebase.udacity.friendlychat

import android.widget.ArrayAdapter
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

/**
 * Created by Nicolas Carrasco S on 7/16/2017.
 */
class MessageEventListener(val adapter: ArrayAdapter<FriendlyMessage>): ChildEventListener{

    override fun onCancelled(p0: DatabaseError?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
        adapter.add(p0?.getValue(FriendlyMessage::class.java))
    }

    override fun onChildRemoved(p0: DataSnapshot?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}