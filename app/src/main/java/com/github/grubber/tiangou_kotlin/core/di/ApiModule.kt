package com.github.grubber.tiangou_kotlin.core.di

import com.github.grubber.tiangou_kotlin.BuildConfig
import com.github.grubber.tiangou_kotlin.core.di.qualifier.ApplicationScope
import com.github.grubber.tiangou_kotlin.data.api.END_POINT
import com.github.grubber.tiangou_kotlin.data.api.service.TopService
import com.github.grubber.tiangou_kotlin.data.exception.ApiException
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import retrofit.Endpoint
import retrofit.RestAdapter
import retrofit.client.Client
import retrofit.client.OkClient
import retrofit.converter.Converter
import retrofit.converter.GsonConverter

/**
 * Created by grubber on 2017/1/6.
 */
@Module
class ApiModule {
    @Provides
    @ApplicationScope
    fun provideEndpoint() = END_POINT

    @Provides
    @ApplicationScope
    fun provideClient(okHttpClient: OkHttpClient): Client = OkClient(okHttpClient)

    @Provides
    @ApplicationScope
    fun provideGson() = GsonBuilder().create()

    @Provides
    @ApplicationScope
    fun provideConverter(gson: Gson): Converter = GsonConverter(gson)

    @Provides
    @ApplicationScope
    fun provideRestAdapter(endpoint: Endpoint, client: Client, converter: Converter): RestAdapter {
        val restAdapter = RestAdapter.Builder().setEndpoint(endpoint).setClient(client).setConverter(converter)
                .setErrorHandler { cause ->
                    ApiException.create(cause)
                }.build()

        if (BuildConfig.DEBUG) {
            restAdapter.logLevel = RestAdapter.LogLevel.FULL
        } else {
            restAdapter.logLevel = RestAdapter.LogLevel.NONE
        }

        return restAdapter
    }

    @Provides
    @ApplicationScope
    fun provideTopService(restAdapter: RestAdapter) = restAdapter.create<TopService>(TopService::class.java)
}