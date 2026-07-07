package com.capgemini.deyvidsilva.lojitas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.capgemini.deyvidsilva.lojitas.data.repository.impl.LojitasRepositoryImpl
import com.capgemini.deyvidsilva.lojitas.domain.usecase.PesquisarProdutoUseCase
import com.capgemini.deyvidsilva.lojitas.navigation.Routes
import com.capgemini.deyvidsilva.lojitas.ui.components.EmptyStateComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.ProdutoCardComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.SearchBarComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.TopBarComponent

@Composable
fun SearchScreen(
    navController: NavController,
    initialQuery: String
) {
    val repository = remember { LojitasRepositoryImpl() }
    val pesquisarProdutoUseCase = remember { PesquisarProdutoUseCase(repository) }

    var currentQuery by remember { mutableStateOf(initialQuery) }

    val produtosEncontrados = remember(currentQuery) {
        pesquisarProdutoUseCase(currentQuery)
    }

    Scaffold(
        topBar = {
            TopBarComponent(
                title = "Resultados para '$currentQuery'",
                canNavigateBack = true,
                onNavigateBack = { navController.popBackStack() },
                showCartAction = true,
                onCartClick = { /* Futuro modal de carrinho */ }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBarComponent(
                onSearch = { novaQuery ->
                    currentQuery = novaQuery
                }
            )

            if (produtosEncontrados.isEmpty()) {
                EmptyStateComponent(message = "Nenhum produto encontrado para '$currentQuery'.\nTente buscar por outros termos.")
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(produtosEncontrados) { produto ->
                        ProdutoCardComponent(
                            produto = produto,
                            onClick = {
                                navController.navigate(Routes.criarRotaDetalhes(produto.id))
                            }
                        )
                    }
                }
            }
        }
    }
}