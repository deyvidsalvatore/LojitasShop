package com.capgemini.deyvidsilva.lojitas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StepItem(step: Int, title: String, isActive: Boolean) {
    val backgroundColor = if (isActive) MaterialTheme.colorScheme.primary else Color.LightGray
    val textColor = if (isActive) MaterialTheme.colorScheme.onPrimary else Color.DarkGray

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(text = step.toString(), color = textColor, fontWeight = FontWeight.Bold)
        }

        if (isActive) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title)
        }
    }
}