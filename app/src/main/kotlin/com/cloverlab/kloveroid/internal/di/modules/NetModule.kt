package com.cloverlab.kloveroid.internal.di.modules

import android.content.Context
import com.cloverlab.kloveroid.api.RestfulApiFactory
import com.cloverlab.kloveroid.api.config.IApiConfig
import com.cloverlab.kloveroid.internal.di.annotations.scopes.Network
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Module
class NetModule {
    @Provides
    @Network
    fun provideConverterGson(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Network
    fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Network
    fun provideGson(): Gson = with(GsonBuilder()) {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        setLenient()
        create()
    }

    @Provides
    @Network
    fun provideOkHttpCache(context: Context): Cache = Cache(context.cacheDir, 10 * 1024 * 1024 /* 10 MiB */)

    @Provides
    @Network
    fun provideOkHttpClient(cache: Cache): OkHttpClient = OkHttpClient.Builder().cache(cache).build()

    @Provides
    @Network
    fun provideBaseRetrofitBuilder(converter: GsonConverterFactory,
                                   callAdapter: RxJava2CallAdapterFactory,
                                   okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder().apply {
            addConverterFactory(converter)
            addCallAdapterFactory(callAdapter)
            client(okHttpClient)
        }

    @Provides
    @Network
    @Named("FakeHttp")
    fun provideRetrofit2(baseBuilder: Retrofit.Builder, restfulApiFactory: RestfulApiFactory): Retrofit =
        with(baseBuilder) {
            val config: IApiConfig = restfulApiFactory.createFakeConfig()
            baseUrl(config.apiBaseUrl)
            build()
        }
}
