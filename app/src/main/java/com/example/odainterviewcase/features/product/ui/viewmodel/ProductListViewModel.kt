package com.example.odainterviewcase.features.product.ui.viewmodel

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItem
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseUiState
import com.example.odainterviewcase.common.data.model.Result
import com.example.odainterviewcase.features.product.domain.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productListRepository: ProductRepository
) : ViewModel() {

    private val _productUiState by lazy { mutableStateOf<OdaInterviewCaseUiState>(
        OdaInterviewCaseUiState.Loading) }
    val productUiState: State<OdaInterviewCaseUiState> by lazy { _productUiState.apply { loadProductList() } }
    private var productsList = listOf<OdaInterviewCaseItem>()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _productUiState.value = OdaInterviewCaseUiState.Error
    }

    private fun loadProductList() {
        viewModelScope.launch(exceptionHandler) {
            _productUiState.value = OdaInterviewCaseUiState.Loading
            getProductList()
        }
    }

    private suspend fun getProductList() {
            productListRepository.getProductList().collect{ result ->
                _productUiState.value = when (result) {
                    is Result.Success -> {
                        productsList= result.data
                        if (productsList.isEmpty() && result.data.isEmpty()) {
                            OdaInterviewCaseUiState.Empty
                        } else {
                            OdaInterviewCaseUiState.ShowOdaInterviewCaseItemsList(odaInterviewCaseItemList = productsList)
                        }
                    }
                    else -> OdaInterviewCaseUiState.Error
                }
            }
    }

}