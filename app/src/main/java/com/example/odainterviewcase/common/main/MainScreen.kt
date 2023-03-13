package com.example.odainterviewcase.common.main

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.odainterviewcase.R
import com.example.odainterviewcase.features.mixed.ui.view.MixedListScreen
import com.example.odainterviewcase.features.product.ui.view.ProductListScreen
import com.example.odainterviewcase.features.recipe.ui.view.RecipeListScreen
import com.example.odainterviewcase.common.theme.purple200

@Composable
 fun OdaInterviewMainScreen (){
    val tabs = listOf("Mixed", "Product","Recipe" )
    var tabIndex by remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex,modifier = Modifier.fillMaxWidth(), containerColor = purple200,   indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[tabIndex])
                    .height(4.dp)
                    .padding(horizontal = 28.dp)
                    .background(color = Color.Cyan, shape = RoundedCornerShape(8.dp))
            )}) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(text = title, color = Color.White) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                )
            }
        }
        when (tabIndex) {
            0 -> MixedListScreen()
            1 -> ProductListScreen()
            2 -> RecipeListScreen()
        }
    }

}

enum class OdaInterviewMixedScreenTab(
    @StringRes val title: Int
) {
    Mixed(R.string.mixed),
    PRODUCT(R.string.product),
    RECIPE(R.string.recipe);

    companion object {
        fun getTabFromResource(@StringRes resource: Int): OdaInterviewMixedScreenTab {
            return when (resource) {
                R.string.product -> PRODUCT
                R.string.recipe -> RECIPE
                else -> Mixed
            }
        }
    }
}





