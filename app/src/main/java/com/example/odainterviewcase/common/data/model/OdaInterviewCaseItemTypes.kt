package com.example.odainterviewcase.common.data.model

sealed class OdaInterviewCaseItemType(val type: String) {
    object Recipe : OdaInterviewCaseItemType("recipe")
    object Product : OdaInterviewCaseItemType("product")
    object RecipeList : OdaInterviewCaseItemType("recipe_list")
    object ProductList : OdaInterviewCaseItemType("product_list")

    override fun toString(): String {
        return type
    }
}








