package com.example.odainterviewcase.features.recipe.data.service

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseModel
import retrofit2.http.GET

interface RecipeService {
    @GET("OdaInterviewCase.json")
    suspend fun getOdaInterviewCaseModel(): OdaInterviewCaseModel
}