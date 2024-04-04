package com.example.upstoxassessment.data.remote.mappers

import com.example.upstoxassessment.data.remote.dtos.StockHoldingResponseDto
import com.example.upstoxassessment.data.utils.DomainMapper
import com.example.upstoxassessment.domain.model.StockHoldingData

class StockHoldingMapper : DomainMapper<StockHoldingResponseDto, List<StockHoldingData>> {
    override fun mapToDomainModel(data: StockHoldingResponseDto): List<StockHoldingData> {
        return data.data?.userHolding?.map {
            StockHoldingData(
                symbol = it.symbol,
                quantity = it.quantity,
                ltp = it.ltp,
                avgPrice = it.avgPrice,
                close = it.close,
                totalPAndL = ((it.ltp!! * it.quantity!!) - (it.avgPrice!! * it.quantity!!))
            )
        } ?: listOf()
    }

}