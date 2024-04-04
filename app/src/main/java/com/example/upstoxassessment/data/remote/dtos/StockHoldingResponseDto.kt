package com.example.upstoxassessment.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class StockHoldingResponseDto(
    @SerializedName("data")
    var data: Data? = Data()
)

data class Data(
    @SerializedName("userHolding")
    var userHolding: ArrayList<UserHolding> = arrayListOf()
)


data class UserHolding(
    @SerializedName("symbol")
    var symbol: String? = null,
    @SerializedName("quantity")
    var quantity: Int? = 0,
    @SerializedName("ltp")
    var ltp: Double? = 0.0,
    @SerializedName("avgPrice")
    var avgPrice: Double? = 0.0,
    @SerializedName("close")
    var close: Double? = null
)