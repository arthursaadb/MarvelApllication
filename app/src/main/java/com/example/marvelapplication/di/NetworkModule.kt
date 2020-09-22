package com.example.marvelapplication.di

import com.example.marvelapplication.BuildConfig
import com.example.marvelapplication.data.network.ApiKeyInterceptor
import com.example.marvelapplication.data.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    val instance = module {
        single { provideLoggingInterceptor() }
        single { provideApiKeyInterceptor(ApiKeyInterceptor()) }
        single { provideGsonConverterFactory() }
        single { provideRxCallAdapterFactory() }
        single { provideOkHttpClient(get(), get()) }
        single { provideRetrofit(get(), BuildConfig.BASE_URL, get(), get()) }
        single { provideApiService(get()) }
    }

    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(apiKeyInterceptor)
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
            }
        }.build()
    }

    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    fun provideApiKeyInterceptor(apiKeyInterceptor: ApiKeyInterceptor) = apiKeyInterceptor

    fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    fun provideRxCallAdapterFactory(): CallAdapter.Factory = RxJava3CallAdapterFactory.create()

    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()


    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}