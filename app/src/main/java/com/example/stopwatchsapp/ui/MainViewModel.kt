package com.example.stopwatchsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stopwatchsapp.data.StopwatchListOrchestrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainViewModelFactory(
    private val stopwatchListOrchestrator: StopwatchListOrchestrator,
    private val viewModelScope: CoroutineScope,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(stopwatchListOrchestrator, viewModelScope) as T
        }
        else throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class MainViewModel(
    private val stopwatchListOrchestrator: StopwatchListOrchestrator,
    private val viewModelScope: CoroutineScope,
) : ViewModel() {

    private var _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> get() = _liveData

    init {
        viewModelScope.launch {
            stopwatchListOrchestrator.stateFlow.collect() {
                _liveData.value = it
            }
        }
    }

    fun start() = stopwatchListOrchestrator.start()
    fun pause() = stopwatchListOrchestrator.pause()
    fun stop() = stopwatchListOrchestrator.stop()

}