package com.uniumuniu.helppagefinder.presentation

import com.uniumuniu.helppagefinder.domain.model.HelpArticle

data class FindArticlesState(
    val articles: List<HelpArticle> = emptyList(),
    val error: String? = null
)