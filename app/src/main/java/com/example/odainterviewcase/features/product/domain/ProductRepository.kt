package com.example.odainterviewcase.features.product.domain

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItem
import com.example.odainterviewcase.common.data.model.Result
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductList(): Flow<Result<List<OdaInterviewCaseItem>>>
}