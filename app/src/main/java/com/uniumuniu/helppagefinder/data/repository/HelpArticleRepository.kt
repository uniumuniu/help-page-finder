package com.uniumuniu.helppagefinder.data.repository


import com.uniumuniu.helppagefinder.data.remote.IHelpPageApi
import com.uniumuniu.helppagefinder.data.remote.Scraper
import com.uniumuniu.helppagefinder.data.remote.dto.toHelpArticles
import com.uniumuniu.helppagefinder.domain.model.HelpArticle
import com.uniumuniu.helppagefinder.domain.repository.IHelpArticleRepository

class HelpArticleRepository(
    private val api: IHelpPageApi,
    private val scraper: Scraper
) : IHelpArticleRepository {

    override suspend fun findHelpArticles(query: String): List<HelpArticle> {
        val articles = api.findHelpArticles(query = query)
        return articles.toHelpArticles()

//        val scraped = scraper.scrapeIt(search)

//        val articleElements = doc.select("article.search-art")
//        Log.d("TAG2", articleElements.html())
//        for (articleElement in articleElements) {
//            val title = articleElement.select("a.search-title h2").`val`()
//            val intro = articleElement.select("p").`val`()
//            Log.d("TAG2", "Title: $title, Intro: $intro")
//        }

        // HtmlUnit
//        WebClient().use {
//            val htmlPage: HtmlPage = it.getPage("https://pomoc.bluemedia.pl/wynikiwyszukiwania?query=Faktura")
//            Log.d("TAG2", htmlPage.textContent)
//        }
    }
}