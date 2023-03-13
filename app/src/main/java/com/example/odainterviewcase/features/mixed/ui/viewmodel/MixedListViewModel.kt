package com.example.odainterviewcase.features.mixed.ui.viewmodel

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItem
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseUiState
import com.example.odainterviewcase.common.data.model.Result
import com.example.odainterviewcase.features.mixed.domain.MixedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MixedListViewModel @Inject constructor(
    private val mixedListRepository: MixedRepository
) : ViewModel() {

    private val _mixedUiState by lazy { mutableStateOf<OdaInterviewCaseUiState>(
        OdaInterviewCaseUiState.Loading) }
    val mixedUiState: State<OdaInterviewCaseUiState> by lazy { _mixedUiState.apply { loadMixedList() } }
    private var mixedList = listOf<OdaInterviewCaseItem>()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _mixedUiState.value = OdaInterviewCaseUiState.Error
    }

    private fun loadMixedList() {
        viewModelScope.launch(exceptionHandler) {
            _mixedUiState.value = OdaInterviewCaseUiState.Loading
            getMixedList()
        }
    }

    private suspend fun getMixedList() {
        mixedListRepository.getMixedList().collect{ result ->
            _mixedUiState.value = when (result) {
                is Result.Success -> {
                    mixedList= result.data.toMutableList()
                    if (mixedList.isEmpty() && result.data.isEmpty()) {
                        OdaInterviewCaseUiState.Empty
                    } else {
                        OdaInterviewCaseUiState.ShowOdaInterviewCaseItemsList(odaInterviewCaseItemList = mixedList)
                    }
                }
                else -> OdaInterviewCaseUiState.Error
            }
        }
    }

}

