package com.github.grubber.tiangou_kotlin.core.di

import com.github.grubber.tiangou_kotlin.core.di.qualifier.ApplicationScope
import dagger.Component

/**
 * Created by grubber on 2017/1/6.
 */
@ApplicationScope
@Component(modules = arrayOf(AndroidModule::class, DataModule::class,
        NetworkModule::class, ApiModule::class, UtilsModule::class))
interface ApplicationComponent : ViewInjector {
    //        fun inject(application: Application)
}