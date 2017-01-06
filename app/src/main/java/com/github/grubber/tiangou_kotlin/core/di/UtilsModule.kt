package com.github.grubber.tiangou_kotlin.core.di

import android.content.Context
import com.github.grubber.tiangou_kotlin.core.di.qualifier.ApplicationScope
import com.github.grubber.tiangou_kotlin.core.di.qualifier.ForApplication
import com.github.grubber.tiangou_kotlin.util.ToastHelper
import dagger.Module
import dagger.Provides

/**
 * Created by grubber on 2017/1/6.
 */
@Module
class UtilsModule {
    @Provides
    @ApplicationScope
    fun provideToastHelper(@ForApplication context: Context) = ToastHelper(context)
}