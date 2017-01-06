package com.github.grubber.tiangou_kotlin.core.di

import android.content.Context
import com.facebook.stetho.okhttp.StethoInterceptor
import com.github.grubber.tiangou_kotlin.BuildConfig
import com.github.grubber.tiangou_kotlin.core.di.qualifier.ApplicationScope
import com.github.grubber.tiangou_kotlin.core.di.qualifier.ForApplication
import com.squareup.okhttp.Cache
import com.squareup.okhttp.OkHttpClient
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by grubber on 2017/1/6.
 */
@Module
class NetworkModule {
    private fun _createOkHttpClient(context: Context): OkHttpClient {
        val okHttpClient = OkHttpClient()
        with(okHttpClient) {
            setConnectTimeout(15000L, TimeUnit.MILLISECONDS)
            setReadTimeout(20000L, TimeUnit.MILLISECONDS)

            if (BuildConfig.DEBUG) {
                networkInterceptors().add(StethoInterceptor())
                cache = Cache(File(context.externalCacheDir, "http-cache"),
                        20 * 1024 * 1024.toLong())
            } else {
                cache = Cache(File(context.cacheDir, "http-cache"), 20 * 1024 * 1024.toLong())
            }

            return this
        }
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(@ForApplication context: Context) = _createOkHttpClient(context)

    @Provides
    @ApplicationScope
    fun providePicasso(@ForApplication context: Context, okHttpClient: OkHttpClient): Picasso {
        val picasso = Picasso.Builder(context).downloader(OkHttpDownloader(okHttpClient))
                .listener { picasso, uri, exception -> Timber.e(exception, "### Failed to load image: %s", uri) }
                .build()

        if (BuildConfig.DEBUG) {
            picasso.setIndicatorsEnabled(true)
        }

        return picasso
    }
}