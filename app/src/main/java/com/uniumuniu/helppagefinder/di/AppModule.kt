package com.uniumuniu.helppagefinder.di

import com.uniumuniu.helppagefinder.core.Constants
import com.uniumuniu.helppagefinder.data.remote.IHelpPageApi
import com.uniumuniu.helppagefinder.data.remote.Scraper
import com.uniumuniu.helppagefinder.data.repository.HelpArticleRepository
import com.uniumuniu.helppagefinder.domain.repository.IHelpArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHelpPageApi(): IHelpPageApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IHelpPageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHelpArticleRepository(scraper: Scraper, api: IHelpPageApi): IHelpArticleRepository {
        return HelpArticleRepository(api = api, scraper = scraper)
    }
}
