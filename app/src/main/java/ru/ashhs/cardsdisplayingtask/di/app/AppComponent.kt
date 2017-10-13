package ru.ashhs.cardsdisplayingtask.di.app

import dagger.Component
import ru.ashhs.cardsdisplayingtask.di.data.DataModule
import ru.ashhs.cardsdisplayingtask.di.network.NetworkModule
import ru.ashhs.cardsdisplayingtask.ui.cards.CardsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, DataModule::class))
interface AppComponent {
    fun inject(target: CardsFragment)
}