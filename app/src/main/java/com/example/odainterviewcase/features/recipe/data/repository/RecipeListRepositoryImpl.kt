package com.example.odainterviewcase.features.recipe.data.repository

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItem
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseModel
import android.content.res.AssetManager
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItemType
import com.example.odainterviewcase.common.data.model.Result
import com.example.odainterviewcase.common.data.model.getResult
import com.example.odainterviewcase.features.recipe.domain.RecipeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RecipeListRepositoryImpl(private val assetManager: AssetManager) : RecipeRepository {

     override fun getRecipeList(): Flow<Result<List<OdaInterviewCaseItem>>> =
        flow {
            // Load the data from the file in the assets folder
            val jsonString =
                assetManager.open("OdaInterviewCase.json").bufferedReader().use { it.readText() }
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val odaInterviewCaseModelAdapter = moshi.adapter(OdaInterviewCaseModel::class.java)
            val odaInterviewCaseModel = odaInterviewCaseModelAdapter.fromJson(jsonString)
            val recipeItemsList = createRecipeItemsList(odaInterviewCaseModel)
            //emit product  Items list
            emit(getResult { recipeItemsList })
        }.catch { e ->
            Timber.tag("RecipeListRepository").e(e, "Error  in fetching json file parsing ")
            emit(Result.Error("Error", e))
        }.flowOn(Dispatchers.IO)

    /*
     * create Product items list based on Product types
     */
    private fun createRecipeItemsList(odaInterviewCase: OdaInterviewCaseModel?): List<OdaInterviewCaseItem> {
        val recipesList = mutableListOf<OdaInterviewCaseItem>()
        odaInterviewCase?.items?.filter { item ->
            when (item.type) {
                OdaInterviewCaseItemType.Recipe.type -> recipesList.add(item)
                OdaInterviewCaseItemType.RecipeList.type -> recipesList.addAll(getRecipeItemFromList(item))
                else -> {false}
            }
        }
        return recipesList.toList()
    }

    /*
    * Fetch Product items from ProductList types
    */
    private fun getRecipeItemFromList(recipeItem: OdaInterviewCaseItem): List<OdaInterviewCaseItem> {
        val recipeList = mutableListOf<OdaInterviewCaseItem>()
        recipeItem.items?.forEach { recipeList.add(it) }
        return recipeList
    }

}