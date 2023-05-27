package com.example.stopwatchsapp

import android.app.Application
import com.example.stopwatchsapp.di.activityModule
import org.koin.core.context.startKoin

class StopwatchApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(activityModule))
        }
    }
}