package com.github.grubber.tiangou_kotlin

import android.content.Context
import com.facebook.stetho.Stetho
import com.github.grubber.tiangou_kotlin.core.di.*
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * Created by grubber on 2017/1/6.
 */
class TGApplication : android.app.Application(), HasComponent<ApplicationComponent> {
    private var _component by Delegates.notNull<ApplicationComponent>()

    override fun onCreate() {
        super.onCreate()

        _setupObjectGraph()
        _setupAnalytics()
    }

    private fun _setupObjectGraph() {
        _component = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .dataModule(DataModule())
                .networkModule(NetworkModule())
                .apiModule(ApiModule())
                .utilsModule(UtilsModule())
                .build()
    }

    private fun _setupAnalytics() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build())
        }
    }

    override fun getComponent(): ApplicationComponent {
        return _component
    }

    companion object {
        fun from(context: Context) = context.applicationContext as TGApplication
    }
}