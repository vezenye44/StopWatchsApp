package com.example.stopwatchsapp.data

import com.example.stopwatchsapp.domain.TimestampProvider
import com.example.stopwatchsapp.domain.model.StopwatchState

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider
) {

    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        return if (currentTimestamp <= state.startTime) {
            state.elapsedTime
        } else {
            currentTimestamp - state.startTime + state.elapsedTime
        }
    }
}