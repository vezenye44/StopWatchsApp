package com.example.stopwatchsapp.domain

fun interface TimestampProvider {
    fun getMilliseconds(): Long
}