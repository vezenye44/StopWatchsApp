package com.example.stopwatchsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatchsapp.data.*
import com.example.stopwatchsapp.databinding.ActivityMainBinding
import com.example.stopwatchsapp.databinding.StopwatchItemLayoutBinding
import com.example.stopwatchsapp.domain.TimestampProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val timestampProvider: TimestampProvider
    private val elapsedTimeCalculator: ElapsedTimeCalculator
    private val stopwatchStateCalculator: StopwatchStateCalculator
    private val stopwatchStateHolder: StopwatchStateHolder
    private val scope: CoroutineScope
    private val stopwatchListOrchestrator: StopwatchListOrchestrator

    init {
        timestampProvider = TimestampProvider { System.currentTimeMillis() }
        elapsedTimeCalculator = ElapsedTimeCalculator(timestampProvider)
        stopwatchStateCalculator =
            StopwatchStateCalculator(timestampProvider, elapsedTimeCalculator)
        stopwatchStateHolder = StopwatchStateHolder(
            stopwatchStateCalculator,
            elapsedTimeCalculator,
            TimestampFormatter()
        )
        scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
        stopwatchListOrchestrator = StopwatchListOrchestrator(stopwatchStateHolder, scope)
    }

    private val viewModel: MainViewModel by lazy {
        MainViewModelFactory(stopwatchListOrchestrator, scope).create(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val calculatorBinding = StopwatchItemLayoutBinding.inflate(
            LayoutInflater.from(this),
            binding.calculatorsContainer,
            false
        ).apply {
            startButton.setOnClickListener { viewModel.start() }
            pauseButton.setOnClickListener { viewModel.pause() }
            stopButton.setOnClickListener { viewModel.stop() }
        }

        binding.calculatorsContainer.addView(calculatorBinding.root)


        viewModel.liveData.observe(this) {
            calculatorBinding.timeTextView.text = it
        }
    }
}