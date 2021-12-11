package com.example.smashnews.util

import android.app.Application
import com.example.smashnews.core.di.appModule
import com.example.smashnews.core.di.repositoryModule
import com.example.smashnews.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(appModule, viewModelModule, repositoryModule))
        }
    }
}