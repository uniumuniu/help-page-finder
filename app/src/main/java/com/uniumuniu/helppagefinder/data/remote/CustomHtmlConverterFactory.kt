package com.uniumuniu.helppagefinder.data.remote

import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.BufferedReader
import java.lang.reflect.Type

class CustomHtmlConverterFactory: Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return Converter<ResponseBody, Document>(function = { body ->
            val stream = body.byteStream()
            val reader = BufferedReader(stream.reader())
            val stringHtml = reader.use {
                it.readText()
            }

            Jsoup.parse(stringHtml)
        })
    }
}