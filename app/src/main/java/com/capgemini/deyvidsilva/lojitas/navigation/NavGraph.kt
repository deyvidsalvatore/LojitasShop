package com.capgemini.deyvidsilva.lojitas.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.capgemini.deyvidsilva.lojitas.domain.entity.CarrinhoItem
import com.capgemini.deyvidsilva.lojitas.domain.entity.Produto
import com.capgemini.deyvidsilva.lojitas.ui.components.CartModalComponent
import com.capgemini.deyvidsilva.lojitas.ui.screens.DetailsScreen
import com.capgemini.deyvidsilva.lojitas.ui.screens.SearchScreen
import com.capgemini.deyvidsilva.lojitas.ui.screens.SplashScreen
import com.capgemini.deyvidsilva.lojitas.ui.screens.home.HomeScreen
@Composable
fun LojitasNavGraph(navController: NavHostController) {
    val carrinhoItens = remember { mutableStateListOf<CarrinhoItem>() }
    var showCartModal by remember { mutableStateOf(false) }

    val onCartClick = { showCartModal = true }

    val adicionarAoCarrinho: (Produto) -> Unit = { produto ->
        val itemExistente = carrinhoItens.find { it.produto.id == produto.id }
        if (itemExistente != null) {
            val index = carrinhoItens.indexOf(itemExistente)
            carrinhoItens[index] = itemExistente.copy(quantidade = itemExistente.quantidade + 1)
        } else {
            carrinhoItens.add(CarrinhoItem(produto = produto, quantidade = 1))
        }
    }

    if (showCartModal) {
        CartModalComponent(
            itens = carrinhoItens,
            onDismiss = { showCartModal = false },
            onIncrease = { item -> adicionarAoCarrinho(item.produto) },
            onDecrease = { item ->
                val index = carrinhoItens.indexOf(item)
                if (item.quantidade > 1) {
                    carrinhoItens[index] = item.copy(quantidade = item.quantidade - 1)
                } else {
                    carrinhoItens.remove(item)
                }
            },
            onCheckoutClick = {
                showCartModal = false
                navController.navigate(Routes.CHECKOUT)
            }
        )
    }

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(route = Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        composable(route = Routes.HOME) {
            HomeScreen(navController = navController, onCartClick = onCartClick)
        }

        composable(
            route = Routes.SEARCH,
            arguments = listOf(navArgument(Arguments.SEARCH_QUERY) { type = NavType.StringType })
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString(Arguments.SEARCH_QUERY) ?: ""
            SearchScreen(navController = navController, initialQuery = query, onCartClick = onCartClick)
        }

        composable(
            route = Routes.DETAILS,
            arguments = listOf(navArgument(Arguments.PRODUCT_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(Arguments.PRODUCT_ID) ?: ""
            DetailsScreen(
                navController = navController,
                productId = productId,
                onCartClick = onCartClick,
                onAddToCart = adicionarAoCarrinho
            )
        }

        composable(route = Routes.CHECKOUT) {
            // CheckoutScreen(navController = navController)
        }

        composable(route = Routes.ORDERS) {
            // OrdersScreen(navController = navController)
        }
    }
}