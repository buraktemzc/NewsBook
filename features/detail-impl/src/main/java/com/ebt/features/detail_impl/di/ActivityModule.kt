package com.ebt.features.detail_impl.di

import com.ebt.features.detail_api.DetailNavigation
import com.ebt.features.detail_impl.navigation.DetailNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal interface ActivityModule {
    @Binds
    fun bindDetailNavigation(detailNavigationImpl: DetailNavigationImpl): DetailNavigation
}