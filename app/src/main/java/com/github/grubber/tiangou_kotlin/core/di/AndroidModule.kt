package com.github.grubber.tiangou_kotlin.core.di

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import com.github.grubber.tiangou_kotlin.core.di.qualifier.ApplicationScope
import com.github.grubber.tiangou_kotlin.core.di.qualifier.ForApplication
import dagger.Module
import dagger.Provides
import dagger.internal.Preconditions

/**
 * Created by grubber on 2017/1/6.
 */
@Module
class AndroidModule {
    private val _context: Context

    constructor(application: Application) {
        _context = Preconditions.checkNotNull(application, "application context can't be null.")
    }

    @Provides
    @ApplicationScope
    @ForApplication
    fun provideApplicationContext() = _context

    @ApplicationScope
    @Provides
    fun provideAssetManager(@ForApplication context: Context): AssetManager {
        return context.assets
    }
}