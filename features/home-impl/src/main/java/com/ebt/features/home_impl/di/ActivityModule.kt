package com.ebt.features.home_impl.di

import com.ebt.features.home_api.HomeNavigation
import com.ebt.features.home_impl.navigation.HomeNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal interface ActivityModule {
    @Binds
    fun bindHomeNavigation(homeNavigationImpl: HomeNavigationImpl): HomeNavigation
}