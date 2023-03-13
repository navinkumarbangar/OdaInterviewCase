package com.example.odainterviewcase.features.mixed.ui.view

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItemAttributes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItemType.Product
import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItemType.Recipe
import com.example.odainterviewcase.features.product.ui.view.ProductListItem
import com.example.odainterviewcase.features.recipe.ui.view.RecipeListItem


@Composable
fun MixedListItem(type:String,mixedAttributes: OdaInterviewCaseItemAttributes) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .zIndex(1f),
        color = Color.White,
        shape = RoundedCornerShape(8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = type.uppercase(), color = Color.Black, style= MaterialTheme.typography.titleMedium)
                Spacer( modifier = Modifier.height(8.dp))
                Divider(Modifier.padding(4.dp))
                when(type){
                    Product.type ->  ProductListItem(mixedAttributes )
                    Recipe.type ->   RecipeListItem(mixedAttributes )
                }
            }
        }
    }
}

