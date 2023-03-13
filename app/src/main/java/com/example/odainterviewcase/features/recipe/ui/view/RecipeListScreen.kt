package com.example.odainterviewcase.features.recipe.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.odainterviewcase.R
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseUiState
import com.example.odainterviewcase.features.recipe.ui.viewmodel.RecipeListViewModel
import timber.log.Timber


@Composable
fun RecipeListScreen(recipeListViewModel: RecipeListViewModel = hiltViewModel()) {
    val recipeListUiState by recipeListViewModel.recipeUiState
    DisplayRecipeList(recipeListUiState)
}

@Composable
private fun DisplayRecipeList(recipeItemsListState: OdaInterviewCaseUiState) {
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxSize().background(Color.LightGray),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ){
        when (recipeItemsListState) {
            is OdaInterviewCaseUiState.Loading -> Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
            is OdaInterviewCaseUiState.Error ->
                Timber.d(" Error while fetching Recipe List ")
            is OdaInterviewCaseUiState.ShowOdaInterviewCaseItemsList -> {
                LazyColumn(state = listState) {
                    items(  recipeItemsListState.odaInterviewCaseItemList) { recipe ->
                        recipe.attributes?.let { RecipeListItem(it) }
                    }

                }
            }
            is OdaInterviewCaseUiState.Empty -> Text(stringResource(R.string.empty_string))
        }
    }

}

