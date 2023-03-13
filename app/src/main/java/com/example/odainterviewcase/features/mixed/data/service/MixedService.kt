package com.example.odainterviewcase.features.mixed.data.service

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseModel
import retrofit2.http.GET

interface MixedService {
    @GET("OdaInterviewCase.json")
    suspend fun getOdaInterviewCaseModel(): OdaInterviewCaseModel
}