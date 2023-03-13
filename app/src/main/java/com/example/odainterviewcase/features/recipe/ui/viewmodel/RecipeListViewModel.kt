package com.example.odainterviewcase.features.recipe.ui.viewmodel

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItem
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseUiState
import com.example.odainterviewcase.common.data.model.Result
import com.example.odainterviewcase.features.recipe.domain.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeListRepository: RecipeRepository
) : ViewModel() {

    private val _recipeUiState by lazy { mutableStateOf<OdaInterviewCaseUiState>(
        OdaInterviewCaseUiState.Loading) }
    val recipeUiState: State<OdaInterviewCaseUiState> by lazy { _recipeUiState.apply { loadRecipeList() } }
    private var recipesList = listOf<OdaInterviewCaseItem>()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _recipeUiState.value = OdaInterviewCaseUiState.Error
    }

    private fun loadRecipeList() {
        viewModelScope.launch(exceptionHandler) {
            _recipeUiState.value = OdaInterviewCaseUiState.Loading
            getRecipeList()
        }
    }

    private suspend fun getRecipeList() {
        recipeListRepository.getRecipeList().collect{ result ->
            _recipeUiState.value = when (result) {
                is Result.Success -> {
                    recipesList= result.data
                    if (recipesList.isEmpty() && result.data.isEmpty()) {
                        OdaInterviewCaseUiState.Empty
                    } else {
                        OdaInterviewCaseUiState.ShowOdaInterviewCaseItemsList(odaInterviewCaseItemList = recipesList)
                    }
                }
                else -> OdaInterviewCaseUiState.Error
            }
        }
    }

}