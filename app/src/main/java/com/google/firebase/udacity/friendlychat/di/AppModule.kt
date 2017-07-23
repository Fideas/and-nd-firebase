package com.google.firebase.udacity.friendlychat.di

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.udacity.friendlychat.BuildConfig.DEBUG
import com.google.firebase.udacity.friendlychat.SignedInActivity
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
    fun providesFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides @Singleton
    fun providesFirebaseRemoteConfigSettings(): FirebaseRemoteConfigSettings {
        return FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(DEBUG)
                .build()
    }

    @Provides @Singleton
    fun providesFirebaseRemoteConfig(settings: FirebaseRemoteConfigSettings): FirebaseRemoteConfig {

        val defaultConfig = HashMap<String, Any>()
        defaultConfig.put(SignedInActivity.FRIENDLY_MSG_LENGTH_KEY, SignedInActivity.DEFAULT_MSG_LENGTH_LIMIT)

        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setConfigSettings(settings)
        remoteConfig.setDefaults(defaultConfig)
        return remoteConfig
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