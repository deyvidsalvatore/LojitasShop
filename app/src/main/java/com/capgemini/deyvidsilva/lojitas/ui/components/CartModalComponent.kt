package com.capgemini.deyvidsilva.lojitas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capgemini.deyvidsilva.lojitas.domain.entity.CarrinhoItem

@Composable
fun CartModalComponent(
    itens: List<CarrinhoItem>,
    onDismiss: () -> Unit,
    onIncrease: (CarrinhoItem) -> Unit,
    onDecrease: (CarrinhoItem) -> Unit,
    onCheckoutClick: () -> Unit
) {
    val valorTotal = itens.sumOf { it.produto.preco * it.quantidade }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Text(
                text = "Seu Carrinho",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (itens.isEmpty()) {
                EmptyStateComponent(message = "Seu carrinho está vazio.")
            } else {
                LazyColumn(modifier = Modifier.weight(1f, fill = false)) {
                    items(itens) { item ->
                        CartItemComponent(
                            item = item,
                            onIncrease = { onIncrease(item) },
                            onDecrease = { onDecrease(item) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Total: R$ ${String.format("%.2f", valorTotal)}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                ButtonComponent(
                    text = "Finalizar Compra",
                    onClick = {
                        onDismiss()
                        onCheckoutClick()
                    }
                )
            }
        }
    }
}