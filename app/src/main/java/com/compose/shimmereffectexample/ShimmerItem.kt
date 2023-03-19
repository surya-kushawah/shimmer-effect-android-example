package com.compose.shimmereffectexample

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerItem(
  isLoading: Boolean, actualContent: @Composable () -> Unit, modifier: Modifier = Modifier
) {
  // If actual content is in loading state , display it shimmer Items
  if (isLoading) {
    Row(modifier = modifier) {
      Box(
        modifier = Modifier
          .size(80.dp)
          .clip(CircleShape)
          .shimmerEffect()
      )
      Spacer(modifier = Modifier.width(16.dp))
      Column(
        modifier = Modifier
          .weight(1f)
          .align(CenterVertically)
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
          modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(20.dp)
            .shimmerEffect()
        )
      }
    }
  } else {
    // If actual content is loaded, display it
    actualContent()
  }
}

private fun Modifier.shimmerEffect(): Modifier = composed {
  var size by remember {
    mutableStateOf(IntSize.Zero)
  }
  val transition = rememberInfiniteTransition()
  val startOffsetX by transition.animateFloat(
    initialValue = -2 * size.width.toFloat(),
    targetValue = 2 * size.width.toFloat(),
    animationSpec = infiniteRepeatable(
      animation = tween(1000)
    )
  )

  background(
    brush = Brush.linearGradient(
      colors = listOf(
        Color(0xFFB9B5B5),
        Color(0xFF837F7F),
        Color(0xFFC2C1C1),
      ),
      start = Offset(startOffsetX, 0f),
      end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
    )
  ).onGloballyPositioned {
    size = it.size
  }
}
