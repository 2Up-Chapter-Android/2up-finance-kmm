package com.aibles.transaction.di

import com.aibles.transaction.data.repository.TransactionRepositoryImpl
import com.aibles.transaction.domain.repository.TransactionRepository
import org.koin.dsl.module

val transactionDomainModule = module {
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
}