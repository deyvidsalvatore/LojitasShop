package com.capgemini.deyvidsilva.lojitas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.capgemini.deyvidsilva.lojitas.data.repository.impl.LojitasRepositoryImpl
import com.capgemini.deyvidsilva.lojitas.domain.usecase.VisualizarDetalhesProdutoUseCase
import com.capgemini.deyvidsilva.lojitas.ui.components.ButtonComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.EmptyStateComponent
import com.capgemini.deyvidsilva.lojitas.ui.components.TopBarComponent

@Composable
fun DetailsScreen(
    navController: NavController,
    productId: String
) {
    val repository = remember { LojitasRepositoryImpl() }
    val detalhesUseCase = remember { VisualizarDetalhesProdutoUseCase(repository) }

    val produto = remember(productId) { detalhesUseCase(productId) }

    Scaffold(
        topBar = {
            TopBarComponent(
                title = "Detalhes do Produto",
                canNavigateBack = true,
                onNavigateBack = { navController.popBackStack() },
                showCartAction = true,
                onCartClick = { /* Futuro modal de carrinho */ }
            )
        },

        bottomBar = {
            if (produto != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    ButtonComponent(
                        text = "Adicionar ao Carrinho - R$ ${String.format("%.2f", produto.preco)}",
                        onClick = {

                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        if (produto != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = produto.imagemProduto),
                    contentDescription = produto.nome,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = produto.categoria.nome.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = produto.nome,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "R$ ${String.format("%.2f", produto.preco)}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Disponível em estoque: ${produto.estoque} unidades",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Descrição",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = produto.descricao,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(80.dp))
            }
        } else {
            EmptyStateComponent(message = "Produto não encontrado.")
        }
    }
}