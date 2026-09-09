package me.kartikarora.icanhazstream

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import me.kartikarora.icanhazstream.navigation.StreamNavGraph
import me.kartikarora.icanhazstream.ui.theme.ICanHazStreamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ICanHazStreamTheme {
                StreamNavGraph()
            }
        }
    }
}
