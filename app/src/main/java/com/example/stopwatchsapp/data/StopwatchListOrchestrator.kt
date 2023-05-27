package com.example.stopwatchsapp.data

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StopwatchListOrchestrator(
    private val stateHolder: StopwatchStateHolder,
    private val scope: CoroutineScope,
) {

    private var job: Job? = null
    private val mutableFlow = MutableStateFlow(stateHolder.getStringTimeRepresentation())
    val stateFlow: StateFlow<String>
        get() = mutableFlow

    fun start() {
        stateHolder.start()
        startJob()
    }

    fun pause() {
        stateHolder.pause()
        stopJob()
    }

    fun stop() {
        stateHolder.stop()
        mutableFlow.value = DEFAULT_VALUE
        stopJob()
    }

    private fun startJob() {
        job = scope.launch {
            while (isActive) {
                mutableFlow.value = stateHolder.getStringTimeRepresentation()
                delay(20L)
            }
        }
    }

    private fun stopJob() {
        job?.cancel()
        job = null
    }

    companion object {
        const val DEFAULT_VALUE = "00:00.000"
    }
}