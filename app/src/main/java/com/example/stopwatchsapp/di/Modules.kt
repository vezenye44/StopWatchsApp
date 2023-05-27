package com.example.stopwatchsapp.di

import com.example.stopwatchsapp.data.ElapsedTimeCalculator
import com.example.stopwatchsapp.data.StopwatchStateCalculator
import com.example.stopwatchsapp.data.StopwatchStateHolder
import com.example.stopwatchsapp.data.TimestampFormatter
import com.example.stopwatchsapp.domain.TimestampProvider
import com.example.stopwatchsapp.ui.MainViewModel
import org.koin.dsl.module

val activityModule = module {
    single { TimestampProvider { System.currentTimeMillis() } }
    single { ElapsedTimeCalculator(get()) }
    single { StopwatchStateCalculator(get(), get()) }
    single { TimestampFormatter() }
    single { StopwatchStateHolder(get(), get(), get()) }

    single { MainViewModel(get(), get(), get()) }
}