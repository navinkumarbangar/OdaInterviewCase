package com.example.odainterviewcase.features.mixed.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.odainterviewcase.R
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseUiState
import com.example.odainterviewcase.features.mixed.ui.viewmodel.MixedListViewModel
import timber.log.Timber


@Composable
fun MixedListScreen(mixedListViewModel: MixedListViewModel = hiltViewModel()) {
    val mixedListUiState by mixedListViewModel.mixedUiState
    DisplayMixedList(mixedListUiState)
}

@Composable
private fun DisplayMixedList(mixedItemsListState: OdaInterviewCaseUiState) {
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxSize().background(Color.LightGray),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ){
        when (mixedItemsListState) {
            is OdaInterviewCaseUiState.Loading -> Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
            is OdaInterviewCaseUiState.Error ->
                Timber.d(" Error while fetching Mixed List ")
            is OdaInterviewCaseUiState.ShowOdaInterviewCaseItemsList -> {
                LazyColumn(state = listState) {
                    items(  mixedItemsListState.odaInterviewCaseItemList) { mixedItem ->
                        mixedItem.attributes?.let { MixedListItem(mixedItem.type,it) }
                        Divider(color = Color.LightGray)
                    }

                }
            }
            is OdaInterviewCaseUiState.Empty -> Text(stringResource(R.string.empty_string))
        }
    }

}


