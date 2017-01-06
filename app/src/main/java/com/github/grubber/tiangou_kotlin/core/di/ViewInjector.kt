package com.github.grubber.tiangou_kotlin.core.di

import com.github.grubber.tiangou_kotlin.ui.HomeActivity
import com.github.grubber.tiangou_kotlin.ui.top.TopActivity

/**
 * Created by grubber on 2017/1/6.
 */
interface ViewInjector {
    fun inject(homeActivity: HomeActivity)
    fun inject(topActivity: TopActivity)
}