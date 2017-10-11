package ru.ashhs.cardsdisplayingtask

import android.app.Application
import ru.ashhs.cardsdisplayingtask.di.app.AppComponent
import ru.ashhs.cardsdisplayingtask.di.app.AppModule
import ru.ashhs.cardsdisplayingtask.di.app.DaggerAppComponent
import ru.ashhs.cardsdisplayingtask.di.network.NetworkModule

/**
 * Created by AleksanderSh on 10.10.2017.
 *
 * Custom implementation of Application with Dagger 2 components.
 */
class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }
}