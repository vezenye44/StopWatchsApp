package com.example.stopwatchsapp.domain.model

sealed interface StopwatchState {
    data class Pause(
        val elapsedTime: Long,
    ) : StopwatchState

    data class Running(
        val elapsedTime: Long,
        val startTime: Long,
    ) : StopwatchState
}