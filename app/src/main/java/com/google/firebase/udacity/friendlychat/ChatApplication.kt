package com.google.firebase.udacity.friendlychat

import android.app.Activity
import android.app.Application
import com.google.firebase.udacity.friendlychat.di.AppComponent
import com.google.firebase.udacity.friendlychat.di.AppModule
import com.google.firebase.udacity.friendlychat.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Nicolas Carrasco S on 7/16/2017.
 */
class ChatApplication : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    val graph: AppComponent by lazy { DaggerAppComponent.create() }

    override fun onCreate() {
        super.onCreate()
        graph.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}