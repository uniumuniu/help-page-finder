package com.uniumuniu.helppagefinder.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.uniumuniu.helppagefinder.R
import com.uniumuniu.helppagefinder.core.Constants
import com.uniumuniu.helppagefinder.domain.model.HelpArticle

@Composable
fun Articles(articles: List<HelpArticle>) {
    LazyColumn {
        items(articles) { article ->
            ArticleItem(article = article)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.standard)))
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ArticlesPreview() {
    Articles(
        articles = listOf(
            HelpArticle(
                title = "Faktury",
                intro = "Tu znajdziesz wszystkie niezbędne informacje związane z fakturami, które możesz opłacić aby wziąć udział w konkursie.",
                link = Constants.BASE_URL
            ),
            HelpArticle(
                title = "Faktury",
                intro = "Tu znajdziesz wszystkie niezbędne informacje związane z fakturami, które możesz opłacić aby wziąć udział w konkursie.",
                link = Constants.BASE_URL
            )
        )
    )
}