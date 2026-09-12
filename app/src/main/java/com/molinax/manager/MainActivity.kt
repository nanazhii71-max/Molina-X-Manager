package com.molinax.manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.molinax.manager.navigation.MolinaXNavHost
import com.molinax.manager.ui.theme.MolinaXManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MolinaXManagerTheme {
                MolinaXNavHost()
            }
        }
    }
}
