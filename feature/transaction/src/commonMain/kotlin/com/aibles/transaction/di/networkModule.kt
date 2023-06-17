package com.aibles.transaction.di

import com.aibles.transaction.data.remote.services.TransactionDataSource
import com.aibles.transaction.data.remote.services.TransactionService
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.dsl.module

val transactionNetworkModule = module {
    single<TransactionService> { get<Ktorfit>().create() }
    single { TransactionDataSource(get()) }
}