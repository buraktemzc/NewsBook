package com.ebt.core.ui.di

import android.content.Context
import com.ebt.core.ui.view.ErrorDialog
import com.ebt.core.ui.view.LoadingDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideErrorDialog(@ActivityContext context: Context): ErrorDialog = ErrorDialog(context)

    @Provides
    fun provideLoadingDialog(@ActivityContext context: Context): LoadingDialog =
        LoadingDialog(context)
}