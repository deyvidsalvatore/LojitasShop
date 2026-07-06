package com.capgemini.deyvidsilva.lojitas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.capgemini.deyvidsilva.lojitas.ui.screens.SplashScreen
import com.capgemini.deyvidsilva.lojitas.ui.screens.home.HomeScreen

@Composable
fun LojitasNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(route = Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        composable(route = Routes.HOME) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Routes.SEARCH,
            arguments = listOf(
                navArgument(Arguments.SEARCH_QUERY) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString(Arguments.SEARCH_QUERY) ?: ""
            // SearchScreen(navController = navController, query = query)
        }

        composable(
            route = Routes.DETAILS,
            arguments = listOf(
                navArgument(Arguments.PRODUCT_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(Arguments.PRODUCT_ID) ?: ""
            // DetailsScreen(navController = navController, productId = productId)
        }

        composable(route = Routes.CHECKOUT) {
            // CheckoutScreen(navController = navController)
        }

        composable(route = Routes.ORDERS) {
            // OrdersScreen(navController = navController)
        }


    }



}
