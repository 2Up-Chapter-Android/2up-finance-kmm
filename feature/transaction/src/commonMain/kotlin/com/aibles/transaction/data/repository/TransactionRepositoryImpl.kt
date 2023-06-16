package com.aibles.transaction.data.repository

import com.aibles.transaction.data.remote.services.TransactionDataSource
import com.aibles.transaction.domain.repository.TransactionRepository

class TransactionRepositoryImpl(private val dataSource: TransactionDataSource) : TransactionRepository {
}