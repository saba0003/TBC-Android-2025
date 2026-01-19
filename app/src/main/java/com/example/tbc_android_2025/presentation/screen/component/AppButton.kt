package com.example.tbc_android_2025.presentation.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.ui.theme.FontSizeMedium

private const val MIN_HEIGHT = 48
private const val BORDER_RADIUS = 6
private const val BORDER_STROKE_WIDTH = 2
private const val PADDING_HORIZONTAL = 24
private const val PADDING_VERTICAL = 8
private const val PRIMARY = true
private const val SHOW_BACKGROUND = true

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPrimary: Boolean = PRIMARY,
    fontSize: TextUnit = FontSizeMedium
) {
    val containerColor = if (isPrimary) Color.Black else Color.White
    val contentColor = if (isPrimary) Color.White else Color.Black
    val border =
        if (isPrimary.not()) BorderStroke(width = BORDER_STROKE_WIDTH.dp, color = Color.Black) else null

    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = MIN_HEIGHT.dp),
        shape = RoundedCornerShape(size = BORDER_RADIUS.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, contentColor = contentColor
        ),
        border = border,
        contentPadding = PaddingValues(
            horizontal = PADDING_HORIZONTAL.dp, vertical = PADDING_VERTICAL.dp
        )
    ) {
        Text(text = text, fontSize = fontSize, fontWeight = FontWeight.Bold)
    }
}

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun AppButtonPreview() =
    AppButton(text = stringResource(id = Strings.app_name), onClick = {})
