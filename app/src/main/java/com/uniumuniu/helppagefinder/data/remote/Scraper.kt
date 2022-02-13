package com.uniumuniu.helppagefinder.data.remote

import android.util.Log
import com.uniumuniu.helppagefinder.core.Constants
import it.skrape.core.htmlDocument
import it.skrape.fetcher.AsyncFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import javax.inject.Inject


class Scraper @Inject constructor() {

    suspend fun scrapeIt(search: String): String {
        val scrapedData = skrape(AsyncFetcher.apply {
            requestBuilder.sslRelaxed = true
        }) {
            request { url = Constants.BASE_URL } // + "/wynikiwyszukiwania?query=$search" }
//            extract {
//                Log.d("TAG3",  htmlDocument { this })
//
//            }
            response { htmlDocument { this } }
        }

//        skrape(BrowserFetcher) {
//            request {
//                url = Constants.BASE_URL //+ "/wynikiwyszukiwania?query=$search"
//            }
//            extract {
//                htmlDocument {
//                    Log.d("TAG3",  text)
//                }
//            }
//        }

        Log.d("TAG3", scrapedData.html)
        return scrapedData.html

//        fun main() {
//            val scrapedData = skrape(BrowserFetcher) {
//                extract {  }
////                e
////                url = "http://some.url"
////                mode = Mode.DOM // <--- here's the magic
////                extract {
////                    div {
////                        withClass = "dynamic"
////                        findFirst { text }
////                    }
////                }
//            }
//            println(scrapedData)
//        }
    }
}
