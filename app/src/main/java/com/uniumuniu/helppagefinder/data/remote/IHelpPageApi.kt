package com.uniumuniu.helppagefinder.data.remote

import com.uniumuniu.helppagefinder.data.remote.dto.ArticleDto
import com.uniumuniu.helppagefinder.data.remote.dto.ArticlesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IHelpPageApi {

    @GET("/search-engine/search?")
    suspend fun findHelpArticles(@Query("query") query: String): ArticlesDto
}