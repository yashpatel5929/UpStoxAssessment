package com.example.upstoxassessment.ui.holdingscreens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upstoxassessment.domain.model.StockHoldingData
import com.example.upstoxassessment.domain.usecases.StockHoldingUseCase
import com.example.upstoxassessment.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoldingScreenViewModel @Inject constructor(
    val stockHoldingUseCase: StockHoldingUseCase
) : ViewModel() {

    private val _loadingState = MutableStateFlow<Boolean>(false)
    val loadingState = _loadingState.asStateFlow()

    private val _stockHoldingData = MutableStateFlow<List<StockHoldingData>>(listOf())
    val stockHoldingData = _stockHoldingData.asStateFlow()

    fun getData() =
        viewModelScope.launch {
            _loadingState.emit(true)
            when(val result = stockHoldingUseCase.invoke()) {
                is Resource.Success -> {
                    _loadingState.emit(false)
                    result.data?.let { _stockHoldingData.emit(it) }
                }

                is Resource.Error -> {
                    _loadingState.emit(false)
                    _stockHoldingData.emit(emptyList())
                }
            }
        }

}