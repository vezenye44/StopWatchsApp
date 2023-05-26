package com.example.stopwatchsapp.data

import com.example.stopwatchsapp.domain.model.StopwatchState
import com.example.stopwatchsapp.domain.model.StopwatchState.Pause
import com.example.stopwatchsapp.domain.model.StopwatchState.Running

class StopwatchStateHolder(
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampFormatter: TimestampFormatter,
) {

    private var currentState: StopwatchState = Pause(0)

    fun start() {
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }

    fun pause() {
        currentState = stopwatchStateCalculator.calculatePauseState(currentState)
    }

    fun stop() {
        currentState = Pause(0)
    }

    fun getStringTimeRepresentation(): String {
        return when (val currentState: StopwatchState = currentState) {
            is Running -> {
                val currentTime = elapsedTimeCalculator.calculate(currentState)
                timestampFormatter.format(currentTime)
            }
            is Pause -> {
                val currentTime = currentState.elapsedTime
                timestampFormatter.format(currentTime)
            }
        }
    }
}