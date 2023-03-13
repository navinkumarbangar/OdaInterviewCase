package com.example.odainterviewcase.features.mixed.domain

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItem
import com.example.odainterviewcase.common.data.model.Result
import kotlinx.coroutines.flow.Flow

interface MixedRepository {
    fun getMixedList(): Flow<Result<List<OdaInterviewCaseItem>>>
}