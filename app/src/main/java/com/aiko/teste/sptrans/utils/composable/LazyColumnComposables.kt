package com.aiko.teste.sptrans.utils.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LineHeader(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = Color.Unspecified,
    onClick: () -> Unit = {},
    displayIcon: Boolean = true
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor,
            modifier = Modifier.weight(1f)
        )
        if (displayIcon) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Open map",
                modifier = Modifier.size(24.dp),
                tint = contentColor
            )
        }
    }
}



@Composable
fun Item(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    displayIcon: Boolean = false
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            modifier = modifier
                .weight(1f)
                .padding(16.dp)
        )
        if (displayIcon) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Open map",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
    }
}