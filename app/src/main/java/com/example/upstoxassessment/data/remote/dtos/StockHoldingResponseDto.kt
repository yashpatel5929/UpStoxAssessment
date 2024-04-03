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
    var quantity: Int? = null,
    @SerializedName("ltp")
    var ltp: Double? = null,
    @SerializedName("avgPrice")
    var avgPrice: Double? = null,
    @SerializedName("close")
    var close: Double? = null
)