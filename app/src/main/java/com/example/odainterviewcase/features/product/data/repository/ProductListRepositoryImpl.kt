package com.example.odainterviewcase.features.product.data.repository

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItem
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseModel
import android.content.res.AssetManager
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItemType
import com.example.odainterviewcase.common.data.model.Result
import com.example.odainterviewcase.common.data.model.getResult
import com.example.odainterviewcase.features.product.domain.ProductRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class ProductListRepositoryImpl(private val assetManager: AssetManager) : ProductRepository {
    override fun getProductList(): Flow<Result<List<OdaInterviewCaseItem>>> =
        flow {
            // Load the data from the file in the assets folder
            val jsonString =
                assetManager.open("OdaInterviewCase.json").bufferedReader().use { it.readText() }
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val odaInterviewCaseModelAdapter = moshi.adapter(OdaInterviewCaseModel::class.java)
            val odaInterviewCaseModel = odaInterviewCaseModelAdapter.fromJson(jsonString)
            val productItemsList = createProductItemsList(odaInterviewCaseModel)
            //emit product  Items list
            emit(getResult { productItemsList })
        }.catch { e ->
            Timber.tag("ProductListRepository").e(e, "Error  in fetching json file parsing ")
            emit(Result.Error("Error", e))
        }.flowOn(Dispatchers.IO)

    /*
     * create Product items list based on Product types
     */
    private fun createProductItemsList(odaInterviewCase: OdaInterviewCaseModel?): List<OdaInterviewCaseItem> {
        val productList = mutableListOf<OdaInterviewCaseItem>()
        odaInterviewCase?.items?.filter { item ->
            when (item.type) {
                OdaInterviewCaseItemType.Product.type -> productList.add(item)
                OdaInterviewCaseItemType.ProductList.type -> productList.addAll(getProductItemFromList(item))
                else -> {false}
            }
        }
        return productList.toList()
    }

    /*
    * Fetch Product items from ProductList types
    */
    private fun getProductItemFromList(productItem: OdaInterviewCaseItem): List<OdaInterviewCaseItem> {
        val productList = mutableListOf<OdaInterviewCaseItem>()
        productItem.items?.forEach { productList.add(it) }
        return productList
    }

}