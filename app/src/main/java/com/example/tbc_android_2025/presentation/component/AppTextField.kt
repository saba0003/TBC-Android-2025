package com.example.tbc_android_2025.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.ui.theme.FontSizeMedium

private const val BORDER_WIDTH = 2
private const val PADDING = 17
private const val INSECURE = false
private const val SHOW_BACKGROUND = true

@Composable
fun AppTextField(
    state: TextFieldState,
    hint: String,
    modifier: Modifier = Modifier,
    isSecure: Boolean = INSECURE,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val fieldModifier = modifier
        .fillMaxWidth()
        .border(width = BORDER_WIDTH.dp, color = Color.Black, shape = RectangleShape)

    val decorator = remember(key1 = state.text.isEmpty(), key2 = hint) {
        decorationContent(state = state, hint = hint)
    }

    if (isSecure)
        BasicSecureTextField(state = state, modifier = fieldModifier, decorator = decorator)
    else
        BasicTextField(
            state = state,
            modifier = fieldModifier,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            decorator = decorator
        )
}

@Composable
private fun AppTextFieldDecorationBox(
    state: TextFieldState, hint: String, innerTextField: @Composable () -> Unit
) = Box(modifier = Modifier.padding(all = PADDING.dp)) {
    if (state.text.isEmpty())
        AppTextFieldHint(hint = hint)
    innerTextField()
}

@Composable
private fun AppTextFieldHint(hint: String) =
    Text(text = hint, color = Color.Gray, fontSize = FontSizeMedium)

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun AppTextFieldPreview() =
    AppTextField(state = TextFieldState(), hint = stringResource(id = Strings.app_name))


/** AUX */
private fun decorationContent(state: TextFieldState, hint: String) = TextFieldDecorator {
    AppTextFieldDecorationBox(state = state, hint = hint, innerTextField = it)
}
