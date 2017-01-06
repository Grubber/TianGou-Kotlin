package com.github.grubber.tiangou_kotlin.core.di

import android.content.Context
import com.github.grubber.tiangou_kotlin.core.EventBus
import com.github.grubber.tiangou_kotlin.core.di.qualifier.ApplicationScope
import com.github.grubber.tiangou_kotlin.core.di.qualifier.ForApplication
import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides

/**
 * Created by grubber on 2017/1/6.
 */
@Module
class DataModule {
    @Provides
    @ApplicationScope
    fun provideSharedPreferences(@ForApplication context: Context) = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun provideEventBus(): Bus = EventBus
}