package com.example.upstoxassessment.data.remote

import com.example.upstoxassessment.data.remote.dtos.StockHoldingResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface UpStoxApi {

    @GET("/")
    suspend fun getUserStockHoldingData(
    ): Response<StockHoldingResponseDto>
}