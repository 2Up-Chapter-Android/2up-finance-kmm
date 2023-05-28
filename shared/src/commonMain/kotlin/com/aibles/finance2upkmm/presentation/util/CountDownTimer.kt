package com.aibles.finance2upkmm.presentation.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class CountDownTimer(
    private val durationMillis: Long,
    private val tickIntervalMillis: Long
) {
    abstract fun onFinish()
    abstract fun onTick(millisUntilFinished: Long)

    private val scope = CoroutineScope(Dispatchers.Main)
    fun start() {
        var remainingMillis = durationMillis

        scope.launch {
            while (remainingMillis > 0) {
                delay(tickIntervalMillis)
                remainingMillis -= tickIntervalMillis
                onTick(remainingMillis)
            }

            onFinish()
        }

        scope.cancel()
    }

    fun cancel() {
        scope.cancel()
    }

}