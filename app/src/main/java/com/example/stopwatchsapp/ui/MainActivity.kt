package com.example.stopwatchsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatchsapp.databinding.ActivityMainBinding
import com.example.stopwatchsapp.databinding.StopwatchItemLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModel()

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


        viewModel.getLiveData().observe(this) {
            calculatorBinding.timeTextView.text = it
        }
    }
}