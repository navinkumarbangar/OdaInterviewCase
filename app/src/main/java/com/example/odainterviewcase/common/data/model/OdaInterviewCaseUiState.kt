package com.example.odainterviewcase.common.data.model

sealed class OdaInterviewCaseUiState {
    object Loading : OdaInterviewCaseUiState()
    object Error : OdaInterviewCaseUiState()
    object Empty : OdaInterviewCaseUiState()
    data class ShowOdaInterviewCaseItemsList(val odaInterviewCaseItemList: List<OdaInterviewCaseItem>) : OdaInterviewCaseUiState()
}