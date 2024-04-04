package com.example.upstoxassessment.domain.model

data class CalculatedData(
    val currentValue: Double,
    val totalInvestment: Double,
    val totalPNL: Double,
    val todaysPNL: Double,
    val absoluteReturn: Double,
)
