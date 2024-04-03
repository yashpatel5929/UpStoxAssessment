package com.example.upstoxassessment.domain.repositories

import com.example.upstoxassessment.domain.model.StockHoldingData
import com.example.upstoxassessment.ui.utils.Resource

interface StockHoldingRepositories {
    suspend fun getStockHoldingData() : Resource<List<StockHoldingData>>
}