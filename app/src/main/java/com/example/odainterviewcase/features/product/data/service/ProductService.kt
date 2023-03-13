package com.example.odainterviewcase.features.product.data.service

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseModel
import retrofit2.http.GET

interface ProductService {
    @GET("OdaInterviewCase.json")
    suspend fun getOdaInterviewCaseModel(): OdaInterviewCaseModel
}