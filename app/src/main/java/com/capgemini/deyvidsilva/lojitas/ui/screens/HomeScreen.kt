package com.capgemini.deyvidsilva.lojitas.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.capgemini.deyvidsilva.lojitas.data.repository.impl.LojitasRepositoryImpl
import com.capgemini.deyvidsilva.lojitas.domain.usecase.VisualizarProdutosUseCase
import com.capgemini.deyvidsilva.lojitas.navigation.Routes
import com.capgemini.deyvidsilva.lojitas.ui.components.BottomNavigationComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.ProdutoCardComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.SearchBarComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.TopBarComponent
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val repository = remember { LojitasRepositoryImpl() }
    val visualizarProdutosUseCase = remember { VisualizarProdutosUseCase(repository) }

    val todosProdutos = remember { visualizarProdutosUseCase() }
    val produtosPorCategoria = remember { todosProdutos.groupBy { it.categoria.nome } }
    val ofertas = remember { todosProdutos.take(5) }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val showFab by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }

    Scaffold(
        topBar = {
            TopBarComponent(
                title = "Shop",
                canNavigateBack = false,
                showCartAction = true,
                showLogo = true,
                onCartClick = { }
            )
        },
        bottomBar = {
            BottomNavigationComponent(
                currentRoute = Routes.HOME,
                onNavigateTo = { rota ->
                    navController.navigate(rota) {
                        popUpTo(Routes.HOME) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = showFab,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Ir para o topo"
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                SearchBarComponent(
                    onSearch = { query ->
                        navController.navigate(Routes.criarRotaPesquisa(query))
                    }
                )
            }

            if (ofertas.isNotEmpty()) {
                item {
                    Text(
                        text = "🔥 Ofertas Especiais",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    LazyRow(contentPadding = PaddingValues(horizontal = 8.dp)) {
                        items(ofertas) { produto ->
                            ProdutoCardComponent(
                                produto = produto,
                                modifier = Modifier.width(180.dp),
                                onClick = {
                                    navController.navigate(Routes.criarRotaDetalhes(produto.id))
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            produtosPorCategoria.forEach { (nomeCategoria, produtosDaCategoria) ->
                item {
                    val iconeCategoria = when (nomeCategoria) {
                        "Eletrônicos" -> Icons.Default.Smartphone
                        "Informática" -> Icons.Default.Computer
                        "Móveis Office" -> Icons.Default.Chair
                        else -> Icons.Default.Category
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = iconeCategoria,
                            contentDescription = "Ícone de $nomeCategoria",
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = nomeCategoria,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    LazyRow(contentPadding = PaddingValues(horizontal = 8.dp)) {
                        items(produtosDaCategoria) { produto ->
                            ProdutoCardComponent(
                                produto = produto,
                                modifier = Modifier.width(180.dp),
                                onClick = {
                                    navController.navigate(Routes.criarRotaDetalhes(produto.id))
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}