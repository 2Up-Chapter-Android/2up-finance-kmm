package com.aibles.finance2upkmm.di

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.builtin.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.builtin.FlowConverterFactory
import de.jensklingenberg.ktorfit.converter.builtin.FlowResponseConverter
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
//    single { provideOkhttpClient() }
    single {
        Ktorfit.Builder()
            .baseUrl("https://2up-finance-service.site/api/v1/")
            .httpClient(provideOkhttpClient())
//            .converterFactories(ResourceResponseConverter())
            .converterFactories(CallConverterFactory())
            .build()
    }
}

private fun provideOkhttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true; prettyPrint = true })
        }
        install(Logging) {
            level = LogLevel.BODY
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.i(tag = "OkhttpClient", message = message)
                }
            }
        }.also {
            Napier.base(DebugAntilog())
        }
    }
}