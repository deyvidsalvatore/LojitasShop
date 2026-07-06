package com.capgemini.deyvidsilva.lojitas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogoComponent(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 28.sp,
    boxSize: Dp = 12.dp,
    textColor: Color = MaterialTheme.colorScheme.primary,
    boxColor: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "L",
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Box(
            modifier = Modifier
                .size(boxSize)
                .background(boxColor)
        )
    }
}