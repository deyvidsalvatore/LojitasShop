package com.capgemini.deyvidsilva.lojitas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun QuantityComponent(
    quantidade: Int,
    estoqueMaximo: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton (onClick = onDecrease) {
            Icon(imageVector = Icons.Default.Remove, contentDescription = "Diminuir")
        }
        Text(
            text = quantidade.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
        IconButton(
            onClick = onIncrease,
            enabled = quantidade < estoqueMaximo
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Aumentar")
        }
    }
}