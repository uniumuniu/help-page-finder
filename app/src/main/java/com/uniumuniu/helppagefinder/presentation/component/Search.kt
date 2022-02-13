package com.uniumuniu.helppagefinder.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.uniumuniu.helppagefinder.*

@ExperimentalComposeUiApi
@Composable
fun Search(query: String, onSearch: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    TextField(
        value = query,
        onValueChange = onSearch,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        placeholder = {
            Text(text = stringResource(id = R.string.search_placeholder))
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
    )

    LaunchedEffect(key1 = Unit) { focusRequester.requestFocus() }
}