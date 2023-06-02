package com.aibles.finance2upkmm.di

import com.aibles.finance2upkmm.data.remote.util.ResourceResponseConverterFactory
import de.jensklingenberg.ktorfit.Ktorfit
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single { provideKtorfit() }
}

private inline fun provideKtorfit(): Ktorfit {
    return Ktorfit.Builder()
        .baseUrl("https://2up-finance-service.site/api/v1/")
//        .baseUrl("http://192.168.1.11:3000/api/v1/")
        .httpClient(provideOkhttpClient())
        .converterFactories(ResourceResponseConverterFactory()).build()
}

private inline fun provideOkhttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true; prettyPrint = true })
        }
        install(DefaultRequest) {
            headers.append(
                HttpHeaders.ContentType,
                ContentType.Application.Json.contentType + "/" + ContentType.Application.Json.contentSubtype
            )
        }
        install(Logging) {
            level = LogLevel.BODY
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.d(tag = "OkhttpClient", message = message)
                }
            }
        }.also {
            Napier.base(DebugAntilog())
        }
    }
}