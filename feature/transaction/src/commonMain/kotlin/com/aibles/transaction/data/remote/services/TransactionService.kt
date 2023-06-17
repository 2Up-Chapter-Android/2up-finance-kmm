package com.aibles.transaction.data.remote.services

import com.aibles.finance2upkmm.data.remote.util.Resource
import com.aibles.transaction.data.remote.dto.login.LoginTestRequest
import com.aibles.transaction.data.remote.dto.login.LoginTestResponse
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface TransactionService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginTestRequest): Resource<LoginTestResponse>
}