package com.example.odainterviewcase.features.product.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.odainterviewcase.R
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseUiState
import com.example.odainterviewcase.features.product.ui.viewmodel.ProductListViewModel
import timber.log.Timber

@Composable
fun ProductListScreen(productListViewModel: ProductListViewModel = hiltViewModel()) {
    val productListUiState by productListViewModel.productUiState
    DisplayProductList(productListUiState)
}

@Composable
private fun DisplayProductList(productItemsListState: OdaInterviewCaseUiState) {
    val listState = rememberLazyListState()
        Column(
            modifier = Modifier
                .fillMaxSize().background(Color.LightGray),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ){
            when (productItemsListState) {
                is OdaInterviewCaseUiState.Loading -> Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
                is OdaInterviewCaseUiState.Error ->
                    Timber.d(" Error while fetching Product List ")
                is OdaInterviewCaseUiState.ShowOdaInterviewCaseItemsList -> {
                    LazyColumn(state = listState) {
                        items(  productItemsListState.odaInterviewCaseItemList) { product ->
                            product.attributes?.let { ProductListItem(it) }
                            Divider(color = Color.LightGray)
                        }

                    }
                }
                is OdaInterviewCaseUiState.Empty -> Text(stringResource(R.string.empty_string))
            }
        }

}


