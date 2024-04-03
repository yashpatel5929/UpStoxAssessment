package com.example.upstoxassessment.data.repositories

import android.util.Log
import com.example.upstoxassessment.data.remote.UpStoxApi
import com.example.upstoxassessment.data.remote.mappers.StockHoldingMapper
import com.example.upstoxassessment.domain.model.StockHoldingData
import com.example.upstoxassessment.domain.repositories.StockHoldingRepositories
import com.example.upstoxassessment.ui.utils.Resource

class StockHoldingRepositoriesImpl(
    private val upStoxApi: UpStoxApi,
    private val stockHoldingMapper: StockHoldingMapper
) : StockHoldingRepositories {
    override suspend fun getStockHoldingData(): Resource<List<StockHoldingData>> {
        return try {
            val response = upStoxApi.getUserStockHoldingData()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.data != null) {
                    val stockData = stockHoldingMapper.mapToDomainModel(responseBody)
                    Resource.Success(stockData)
                } else {
                    Resource.Error(message = "Something went wrong")
                }
            } else {
                Resource.Error(message = "Something went wrong")
            }
        } catch (e: Exception) {
            Log.d("TAG", "getStockHoldingData: ${e.message}")
            Resource.Error(message = "${e.message}")
        }
    }
}