package com.google.firebase.udacity.friendlychat.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Nicolas Carrasco S on 7/16/2017.
 */
@Module
class AppModule{

    @Provides @Singleton
    fun providesFirebase(): FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }

    @Provides @Singleton @Named("messages")
    fun messageDatabaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference{
        return firebaseDatabase.reference.child("messages")
    }
}