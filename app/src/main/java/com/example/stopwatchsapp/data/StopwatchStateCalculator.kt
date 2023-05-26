package com.example.stopwatchsapp.data

import com.example.stopwatchsapp.domain.TimestampProvider
import com.example.stopwatchsapp.domain.model.StopwatchState
import com.example.stopwatchsapp.domain.model.StopwatchState.Pause
import com.example.stopwatchsapp.domain.model.StopwatchState.Running

class StopwatchStateCalculator(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
) {

    fun calculateRunningState(oldState: StopwatchState): StopwatchState {
        return when (oldState) {
            is Running -> oldState
            is Pause -> Running(
                elapsedTime = oldState.elapsedTime,
                startTime = timestampProvider.getMilliseconds()
            )
        }
    }

    fun calculatePauseState(oldState: StopwatchState): StopwatchState {
        return when (oldState) {
            is Running -> Pause(
                elapsedTime = elapsedTimeCalculator.calculate(oldState)
            )
            is Pause -> oldState
        }
    }
}