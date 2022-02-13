package com.uniumuniu.helppagefinder.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.uniumuniu.helppagefinder.R
import com.uniumuniu.helppagefinder.core.Constants
import com.uniumuniu.helppagefinder.domain.model.HelpArticle

@Composable
fun ArticleItem(article: HelpArticle) {
    val localUriHandler = LocalUriHandler.current
    Card {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.standard))) {
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = MaterialTheme.colors.primary)) {
                        append(article.title)
                    }
                },
                style = MaterialTheme.typography.h5,
                onClick = {
                    localUriHandler.openUri(article.link)
                })

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small)))
            Text(text = article.intro)
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ArticleItemPreview() {
    ArticleItem(
        article = HelpArticle(
            title = "Faktury",
            intro = "Tu znajdziesz wszystkie niezbędne informacje związane z fakturami, które możesz opłacić aby wziąć udział w konkursie.",
            link = Constants.BASE_URL
        )
    )
}