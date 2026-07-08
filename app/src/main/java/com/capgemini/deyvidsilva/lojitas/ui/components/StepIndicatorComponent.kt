package com.capgemini.deyvidsilva.lojitas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StepIndicatorComponent(currentStep: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StepItem(step = 1, title = "Endereço", isActive = currentStep >= 1)
        HorizontalDivider(modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
        StepItem(step = 2, title = "Pagamento", isActive = currentStep >= 2)
        HorizontalDivider(modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
        StepItem(step = 3, title = "Resumo", isActive = currentStep >= 3)
    }
}