package com.example.spreng.ui.custom

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitTouchSlopOrCancellation
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.pressHandling(
    onPressChanged: (Boolean) -> Unit
): Modifier = this.then(
    Modifier.pointerInput(Unit) {
        awaitEachGesture {
            val down = awaitFirstDown()
            onPressChanged(true)

            var isPressed = true
            while (isPressed) {
                val change = awaitTouchSlopOrCancellation(down.id) { _, _ -> }
                if (change == null || !change.pressed) {
                    isPressed = false
                } else {
                    val eventPosition = change.position
                    val insideBounds = eventPosition.x in 0f..size.width.toFloat() &&
                            eventPosition.y in 0f..size.height.toFloat()
                    onPressChanged(insideBounds)
                }
            }

            onPressChanged(false)
        }
    }
)