package com.example.upstoxassessment.di

import com.example.upstoxassessment.data.remote.UpStoxApi
import com.example.upstoxassessment.data.remote.mappers.StockHoldingMapper
import com.example.upstoxassessment.data.repositories.StockHoldingRepositoriesImpl
import com.example.upstoxassessment.domain.repositories.StockHoldingRepositories
import com.example.upstoxassessment.domain.usecases.StockHoldingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StockHoldingModule {

    @Provides
    @Singleton
    fun getStockHoldingDataMapper() : StockHoldingMapper{
        return StockHoldingMapper()
    }


    @Provides
    @Singleton
    fun getStockHoldingRepositories(
        upStoxApi: UpStoxApi,
        stockHoldingMapper: StockHoldingMapper
    ) : StockHoldingRepositories {
        return StockHoldingRepositoriesImpl(upStoxApi,stockHoldingMapper)
    }

    @Provides
    @Singleton
    fun provideGetStockHoldingUseCase(
        stockHoldingRepositories: StockHoldingRepositories
    ): StockHoldingUseCase {
        return StockHoldingUseCase(stockHoldingRepositories)
    }
}