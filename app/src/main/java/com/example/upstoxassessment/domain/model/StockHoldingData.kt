package com.example.upstoxassessment.domain.model

data class StockHoldingData(
    val symbol: String?,
    val quantity: Int?,
    val ltp: Double?,
    val avgPrice: Double?,
    val close: Double?,
    val totalPAndL: Double?,
)
