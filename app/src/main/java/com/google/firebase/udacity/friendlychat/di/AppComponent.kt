package com.google.firebase.udacity.friendlychat.di

import com.google.firebase.udacity.friendlychat.ChatApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Nicolas Carrasco S on 7/16/2017.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        AndroidInjectionModule::class,
        ActivityInjectionModule::class))
interface AppComponent {
    fun inject(app: ChatApplication)
}