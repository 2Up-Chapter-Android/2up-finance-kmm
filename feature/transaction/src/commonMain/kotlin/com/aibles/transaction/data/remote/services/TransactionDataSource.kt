package com.aibles.transaction.data.remote.services

import com.aibles.transaction.data.remote.dto.login.LoginTestRequest


class TransactionDataSource(private val service: TransactionService) {
    suspend fun login(loginRequest: LoginTestRequest) = service.login(loginRequest)

}