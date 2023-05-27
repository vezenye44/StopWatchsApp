package com.example.stopwatchsapp.ui

 import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stopwatchsapp.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainViewModel(
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val timestampFormatter: TimestampFormatter,
    private val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob()),
) : ViewModel() {

    private val stateHolder = StopwatchStateHolder(
        stopwatchStateCalculator,
        elapsedTimeCalculator,
        timestampFormatter
    )
    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        stateHolder,
        viewModelScope
    )
    private val liveData = stopwatchListOrchestrator.stateFlow.asLiveData()

    fun getLiveData(): LiveData<String> {
        return liveData
    }

    fun start() = stopwatchListOrchestrator.start()
    fun pause() = stopwatchListOrchestrator.pause()
    fun stop() = stopwatchListOrchestrator.stop()
}