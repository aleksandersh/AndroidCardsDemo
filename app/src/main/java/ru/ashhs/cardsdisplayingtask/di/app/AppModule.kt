package ru.ashhs.cardsdisplayingtask.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    @Named("WorkerThread")
    fun provideWorkerScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("UiThread")
    fun provideUiScheduler() = AndroidSchedulers.mainThread()!!
}