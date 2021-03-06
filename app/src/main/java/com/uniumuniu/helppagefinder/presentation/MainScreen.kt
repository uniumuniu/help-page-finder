package com.uniumuniu.helppagefinder.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.uniumuniu.helppagefinder.R
import com.uniumuniu.helppagefinder.core.Constants
import com.uniumuniu.helppagefinder.domain.model.HelpArticle
import com.uniumuniu.helppagefinder.presentation.component.Articles
import com.uniumuniu.helppagefinder.presentation.component.Search
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is MainViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    val state = viewModel.state
    Scaffold(scaffoldState = scaffoldState) {
        MainScreenStateless(
            articles = state.value.articles,
            viewModel.searchQuery.value
        ) { viewModel.onSearch(it) }
    }
}

@ExperimentalComposeUiApi
@Composable
fun MainScreenStateless(articles: List<HelpArticle>, query: String, onSearch: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.standard)),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Search(query = query, onSearch = onSearch)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.standard)))
            Articles(articles = articles)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun MainScreenPreview() {
    MainScreenStateless(
        articles = listOf(
            HelpArticle(
                title = "Faktury",
                intro = "Tu znajdziesz wszystkie niezb??dne informacje zwi??zane z fakturami, kt??re mo??esz op??aci?? aby wzi???? udzia?? w konkursie.",
                link = Constants.BASE_URL
            ),
            HelpArticle(
                title = "Faktury",
                intro = "Tu znajdziesz wszystkie niezb??dne informacje zwi??zane z fakturami, kt??re mo??esz op??aci?? aby wzi???? udzia?? w konkursie.",
                link = Constants.BASE_URL
            )
        ),
        "Faktury",
        onSearch = {})
}