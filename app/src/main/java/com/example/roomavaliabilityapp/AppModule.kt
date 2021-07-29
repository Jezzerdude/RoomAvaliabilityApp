package com.example.roomavaliabilityapp

import com.example.roomavaliabilityapp.network.api.PeopleApi
import com.example.roomavaliabilityapp.network.api.RoomApi
import com.example.roomavaliabilityapp.utility.NetworkConsts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl(): String =
        NetworkConsts.BASE_URL


    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    fun providesHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .callTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    @Provides
    fun providesGsonConvereterFactory(): Converter.Factory =
        GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideRoomApi(retrofit: Retrofit): RoomApi =
        retrofit.create(RoomApi::class.java)

    @Provides
    fun providePeopleApi(retrofit: Retrofit): PeopleApi =
        retrofit.create(PeopleApi::class.java)

    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}