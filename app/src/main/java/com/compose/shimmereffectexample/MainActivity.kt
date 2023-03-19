package com.compose.shimmereffectexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.shimmereffectexample.ui.theme.ShimmerEffectExampleTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ShimmerEffectExampleTheme {
        var isLoading by remember {
          mutableStateOf(true)
        }
        LaunchedEffect(key1 = true) {
          delay(2000)
          isLoading = false
        }
        // Call Shimmer effect using Jetpack Compose
        ShimmerEffectListView(isLoading)
      }
    }
  }
}

@Composable
fun ShimmerEffectListView(isLoading: Boolean) {
  LazyColumn(
    modifier = Modifier.fillMaxSize()
  ) {
    items(20) {
      ShimmerItem(
        isLoading = isLoading,
        actualContent = { ActualContent() },
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      )
    }
  }
}

/**
 * Actual Listview that you want to display once shimmer goes out
 */
@Composable
fun ActualContent() {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
  ) {
    Icon(
      imageVector = Icons.Default.AccountCircle,
      contentDescription = null,
      modifier = Modifier.size(80.dp)
    )
    Spacer(modifier = Modifier.width(16.dp))
    Text(
      text = "This is a  text to show that our shimmer loading effect " + "is looking good",
      modifier = Modifier.align(CenterVertically)
    )
  }
}