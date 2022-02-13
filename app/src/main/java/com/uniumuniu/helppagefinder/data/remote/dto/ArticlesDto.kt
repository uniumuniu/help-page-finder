package com.uniumuniu.helppagefinder.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.uniumuniu.helppagefinder.domain.model.HelpArticle

data class ArticlesDto(
    @SerializedName("items")
    val items: List<ArticleDto>,
)

fun ArticlesDto.toHelpArticles(): List<HelpArticle> {
    return items.map { it.toHelpArticle() }
}