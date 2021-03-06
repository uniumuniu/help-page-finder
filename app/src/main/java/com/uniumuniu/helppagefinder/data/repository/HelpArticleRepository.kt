package com.uniumuniu.helppagefinder.data.repository


import com.google.gson.Gson
import com.uniumuniu.helppagefinder.data.remote.IHelpPageApi
import com.uniumuniu.helppagefinder.data.remote.dto.toHelpArticles
import com.uniumuniu.helppagefinder.domain.model.HelpArticle
import com.uniumuniu.helppagefinder.domain.repository.IHelpArticleRepository

class HelpArticleRepository(
    private val api: IHelpPageApi,
    private val gson: Gson
) : IHelpArticleRepository {

    override suspend fun findHelpArticles(query: String): List<HelpArticle> {
        val articles = api.findHelpArticles(query = query)
        return articles.toHelpArticles(gson)
    }
}