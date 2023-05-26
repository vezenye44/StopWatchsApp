package com.example.stopwatchsapp.data

class TimestampFormatter() {

    fun format(timestamp: Long): String {
        val milliseconds = timestamp % 1000
        val millisecondsFormat = milliseconds.toString().padStart(3, '0')

        val seconds = timestamp / 1000
        val secondsFormat = (seconds % 60).toString().padStart(2, '0')

        val minutes = seconds / 60
        val minutesFormat = (minutes % 60).toString().padStart(2, '0')

        val hours = minutes / 60
        val hoursFormat = hours.toString().padStart(2, '0')

        return if (hours > 0) {
            "$hoursFormat:$minutesFormat:$secondsFormat"
        } else {
            "$minutesFormat:$secondsFormat.$millisecondsFormat"
        }
    }

    companion object {
        const val DEFAULT_TIME = "00:00:000"
    }
}