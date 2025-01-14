package com.ebt.newsbook.di

import android.app.Activity
import com.ebt.features.home_impl.listener.HomeListener
import com.ebt.newsbook.MainActivity
import com.ebt.newsbook.listener.HomeListenerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
interface ActivityModule {

    @Binds
    fun bindHomeListener(homeListenerImpl: HomeListenerImpl): HomeListener

    companion object {
        @Provides
        @ActivityScoped
        fun provideMainActivity(activity: Activity): MainActivity = activity as MainActivity
    }
}