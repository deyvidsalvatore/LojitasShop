package com.capgemini.deyvidsilva.lojitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.capgemini.deyvidsilva.lojitas.navigation.LojitasNavGraph
import com.capgemini.deyvidsilva.lojitas.ui.theme.LojitasShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LojitasShopTheme {
                val navController = rememberNavController()
                LojitasNavGraph(navController = navController)
            }
        }
    }
}