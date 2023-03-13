package com.example.odainterviewcase.features.mixed.data.repository

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItem
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseModel
import android.content.res.AssetManager
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItemType.*
import com.example.odainterviewcase.common.data.model.Result
import com.example.odainterviewcase.common.data.model.getResult
import com.example.odainterviewcase.features.mixed.domain.MixedRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class MixedListRepositoryImpl(private val assetManager: AssetManager): MixedRepository {
    override fun getMixedList(): Flow<Result<List<OdaInterviewCaseItem>>> =
        flow {
            // Load the data from the file in the assets folder
            val jsonString =
                assetManager.open("OdaInterviewCase.json").bufferedReader().use { it.readText() }
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val odaInterviewCaseModelAdapter = moshi.adapter(OdaInterviewCaseModel::class.java)
            val odaInterviewCaseModel = odaInterviewCaseModelAdapter.fromJson(jsonString)
            val mixedItemsList = createMixedItemsList(odaInterviewCaseModel)
            //emit product  Items list
            emit(getResult { mixedItemsList })
        }.catch { e ->
            Timber.tag("RecipeListRepository").e(e, "Error  in fetching json file parsing ")
            emit(Result.Error("Error", e))
        }.flowOn(Dispatchers.IO)

    /*
     * create Mixed items list based on types
     */
    private fun createMixedItemsList(odaInterviewCase: OdaInterviewCaseModel?): List<OdaInterviewCaseItem> {
        val mixedList = mutableListOf<OdaInterviewCaseItem>()
        odaInterviewCase?.items?.filter { item ->
            when (item.type) {
                Recipe.type -> mixedList.add(item)
                RecipeList.type -> mixedList.addAll(getMixedItemFromList(item))
                Product.type -> mixedList.add(item)
                ProductList.type->mixedList.addAll(getMixedItemFromList(item))
                else -> {false}
            }
        }
        return mixedList.toList()
    }

    /*
    * Get Mixed items from List types
    */
    private fun getMixedItemFromList(mixedItem: OdaInterviewCaseItem): List<OdaInterviewCaseItem> {
        val mixedList = mutableListOf<OdaInterviewCaseItem>()
        mixedItem.items?.forEach { mixedList.add(it) }
        return mixedList
    }

}