package ru.ashhs.cardsdisplayingtask.di.network

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.ashhs.cardsdisplayingtask.network.RoutesServiceApi
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        const val BASE_URL_JSON_PLACEHOLDER = "https://jsonplaceholder.typicode.com/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_JSON_PLACEHOLDER)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideRoutesServiceApi(retrofit: Retrofit): RoutesServiceApi {
        return retrofit.create(RoutesServiceApi::class.java)
    }
}