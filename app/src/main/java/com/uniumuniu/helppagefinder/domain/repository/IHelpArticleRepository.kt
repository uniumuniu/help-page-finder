package com.uniumuniu.helppagefinder.domain.repository

import com.uniumuniu.helppagefinder.domain.model.HelpArticle

interface IHelpArticleRepository {
    suspend fun findHelpArticles(query: String): List<HelpArticle>
}