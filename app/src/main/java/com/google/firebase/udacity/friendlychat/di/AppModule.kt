package com.google.firebase.udacity.friendlychat.di

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Nicolas Carrasco S on 7/16/2017.
 */
@Module
class AppModule {

    @Provides @Singleton
    fun providesFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides @Singleton
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides @Singleton
    fun providesAuthUI(): AuthUI {
        return AuthUI.getInstance()
    }

    @Provides @Singleton
    fun providesFirebaseStorage(): FirebaseStorage{
        return FirebaseStorage.getInstance()
    }

    @Provides @Singleton @Named("messages")
    fun messageDatabaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.reference.child("messages")
    }

    @Provides @Singleton @Named("chat_photos")
    fun chatPhotoStorageReference(firebaseStorage: FirebaseStorage): StorageReference {
        return firebaseStorage.reference.child("chat_photos")
    }
}