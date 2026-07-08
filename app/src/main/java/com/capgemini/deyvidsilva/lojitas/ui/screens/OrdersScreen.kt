package com.capgemini.deyvidsilva.lojitas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
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
import com.capgemini.deyvidsilva.lojitas.domain.entity.Pedido
import com.capgemini.deyvidsilva.lojitas.domain.usecase.ConsultarMinhasComprasUseCase
import com.capgemini.deyvidsilva.lojitas.navigation.Routes
import com.capgemini.deyvidsilva.lojitas.ui.components.BottomNavigationComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.EmptyStateComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.OrderCardComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.OrderDetailsModalComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.TopBarComponent

@Composable
fun OrdersScreen(navController: NavController) {
    val repository = remember { LojitasRepositoryImpl() }
    val consultarMinhasComprasUseCase = remember { ConsultarMinhasComprasUseCase(repository) }

    val pedidos = remember { consultarMinhasComprasUseCase() }

    var pedidoSelecionado by remember { mutableStateOf<Pedido?>(null) }

    pedidoSelecionado?.let { pedido ->
        OrderDetailsModalComponent(
            pedido = pedido,
            onDismiss = { pedidoSelecionado = null }
        )
    }

    Scaffold(
        topBar = {
            TopBarComponent(
                title = "Meus Pedidos",
                canNavigateBack = false,
                showLogo = false
            )
        },
        bottomBar = {
            BottomNavigationComponent(
                currentRoute = Routes.ORDERS,
                onNavigateTo = { rota ->
                    navController.navigate(rota) {
                        popUpTo(Routes.HOME) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (pedidos.isEmpty()) {
                EmptyStateComponent(
                    message = "Você ainda não possui nenhuma compra.",
                    icon = Icons.AutoMirrored.Filled.ReceiptLong
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(pedidos.reversed()) { pedido ->
                        OrderCardComponent(
                            pedido = pedido,
                            onClick = { pedidoSelecionado = pedido }
                        )
                    }
                }
            }
        }
    }
}