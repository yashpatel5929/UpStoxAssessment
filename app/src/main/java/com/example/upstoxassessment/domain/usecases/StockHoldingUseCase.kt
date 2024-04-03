package com.example.upstoxassessment.domain.usecases

import com.example.upstoxassessment.domain.model.StockHoldingData
import com.example.upstoxassessment.domain.repositories.StockHoldingRepositories
import com.example.upstoxassessment.ui.utils.Resource

class StockHoldingUseCase(
    private val stockHoldingRepositories: StockHoldingRepositories
) {
    suspend operator fun invoke(): Resource<List<StockHoldingData>> {
      return stockHoldingRepositories.getStockHoldingData()
    }
}