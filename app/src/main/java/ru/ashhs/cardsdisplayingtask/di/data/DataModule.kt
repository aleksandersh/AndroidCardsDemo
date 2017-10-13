package ru.ashhs.cardsdisplayingtask.di.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import ru.ashhs.cardsdisplayingtask.data.sharedpreferences.SharedPreferencesHelper
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    @Named("DefaultSharedPreferences")
    fun provideDefaultSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(
            @Named("DefaultSharedPreferences") sharedPreferences: SharedPreferences):
            SharedPreferencesHelper {
        return SharedPreferencesHelper(sharedPreferences)
    }
}