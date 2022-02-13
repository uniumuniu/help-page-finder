package com.uniumuniu.helppagefinder.data.remote.dto


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.uniumuniu.helppagefinder.core.Constants
import com.uniumuniu.helppagefinder.domain.model.HelpArticle

data class ArticleDto(
//    @SerializedName("enabled")
//    val enabled: Int,
//    @SerializedName("id")
//    val id: Int,
//    @SerializedName("labels")
//    val labels: Any,
    @SerializedName("link")
    val link: String,
//    @SerializedName("position")
//    val position: Int,
    @SerializedName("preview")
    val preview: String?,
//    @SerializedName("text")
//    val text: String,
    @SerializedName("title")
    val title: String,
//    @SerializedName("type")
//    val type: String
)

fun ArticleDto.toHelpArticle(): HelpArticle {
    return HelpArticle(
        title = title,
        intro = Gson().fromJson(preview, PreviewDto::class.java).text,
        link = Constants.BASE_URL + link
    )
}