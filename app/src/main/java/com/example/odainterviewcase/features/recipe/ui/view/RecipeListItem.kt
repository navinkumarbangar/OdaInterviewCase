package com.example.odainterviewcase.features.recipe.ui.view

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItemAttributes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun RecipeListItem(recipeAttributes: OdaInterviewCaseItemAttributes) {
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
                recipeAttributes.title?.let { Text(text = it, color = Color.Black, style= MaterialTheme.typography.titleSmall) }
                Text(text = "Cooking time: ${recipeAttributes.cooking_duration_string}")
                Text(text = "Difficulty: ${recipeAttributes.difficulty}")
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(modifier = Modifier.padding(4.dp)) {
                if (recipeAttributes.is_liked_by_user == true) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = recipeAttributes.like_count.toString(),
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }


                }
            }
        }
    }
}
