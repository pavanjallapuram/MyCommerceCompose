package com.example.mycommercejetpack

import com.example.mycommercejetpack.utils.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [com.example.mycommercejetpack.modules.NetworkModule::class] // replace real network module
)
object TestNetworkModule {

    @Provides
    @Singleton
    fun provideMockWebServer(): MockWebServer = MockWebServer()

    @Provides
    @Singleton
    fun provideRetrofit(mockWebServer: MockWebServer): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Util.BASE_URL) // use mock server
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApiService(retrofit: Retrofit): com.example.mycommercejetpack.listeners.ProductApiService {
        return retrofit.create(com.example.mycommercejetpack.listeners.ProductApiService::class.java)
    }
}