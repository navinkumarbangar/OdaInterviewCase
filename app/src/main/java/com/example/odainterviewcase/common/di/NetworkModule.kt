package com.example.odainterviewcase.common.di

import com.example.odainterviewcase.features.mixed.data.service.MixedService
import com.example.odainterviewcase.features.product.data.service.ProductService
import com.example.odainterviewcase.features.recipe.data.service.RecipeService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
        baseUrl: String,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()


    @Provides
    @Singleton
    fun provideMixedService(retrofit: Retrofit): MixedService
    = retrofit.create(MixedService::class.java)

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductService
            = retrofit.create(ProductService::class.java)

    @Provides
    @Singleton
    fun provideRecipeService(retrofit: Retrofit): RecipeService
            = retrofit.create(RecipeService::class.java)

}