package com.example.odainterviewcase.features.recipe.domain

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItem
import com.example.odainterviewcase.common.data.model.Result
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipeList(): Flow<Result<List<OdaInterviewCaseItem>>>
}