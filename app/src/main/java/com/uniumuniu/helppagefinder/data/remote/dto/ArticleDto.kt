package com.uniumuniu.helppagefinder.data.remote.dto


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.uniumuniu.helppagefinder.core.Constants
import com.uniumuniu.helppagefinder.domain.model.HelpArticle

data class ArticleDto(
    @SerializedName("link")
    val link: String,
    @SerializedName("preview")
    val preview: String?,
    @SerializedName("title")
    val title: String,
)

fun ArticleDto.toHelpArticle(gson: Gson): HelpArticle {
    return HelpArticle(
        title = title.trim(),
        intro = gson.fromJson(preview, PreviewDto::class.java).text.trim(),
        link = Constants.BASE_URL + link
    )
}