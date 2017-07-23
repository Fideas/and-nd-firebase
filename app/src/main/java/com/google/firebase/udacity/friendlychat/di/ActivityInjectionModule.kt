package com.google.firebase.udacity.friendlychat.di

import com.google.firebase.udacity.friendlychat.SignedInActivity
import com.google.firebase.udacity.friendlychat.SignedOutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Nicolas Carrasco S on 7/16/2017.
 */
@Module
abstract class ActivityInjectionModule{
    @ContributesAndroidInjector
    abstract fun contributesSignedInActivity(): SignedInActivity

    @ContributesAndroidInjector
    abstract fun contributesSignedOutActivity(): SignedOutActivity
}