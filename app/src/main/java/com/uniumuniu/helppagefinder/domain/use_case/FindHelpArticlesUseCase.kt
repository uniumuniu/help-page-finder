package com.uniumuniu.helppagefinder.domain.use_case

import com.uniumuniu.helppagefinder.core.Resource
import com.uniumuniu.helppagefinder.domain.model.HelpArticle
import com.uniumuniu.helppagefinder.domain.repository.IHelpArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FindHelpArticlesUseCase @Inject constructor(
    private val repository: IHelpArticleRepository
) {
    operator fun invoke(search: String): Flow<Resource<List<HelpArticle>>> = flow {
        try {
            val articles = repository.findHelpArticles(query = search)
            emit(Resource.Success<List<HelpArticle>>(articles))
        } catch (e: HttpException) {
            emit(Resource.Error<List<HelpArticle>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<HelpArticle>>("Couldn't reach the server. Please check your internet connection."))
        }
    }.flowOn(Dispatchers.IO)
}