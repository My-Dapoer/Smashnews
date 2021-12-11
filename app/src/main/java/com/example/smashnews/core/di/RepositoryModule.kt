package com.example.smashnews.core.di

import com.example.smashnews.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
}