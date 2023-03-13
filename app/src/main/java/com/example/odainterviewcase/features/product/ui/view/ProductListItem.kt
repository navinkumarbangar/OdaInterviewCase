package com.example.odainterviewcase.features.product.ui.view

import com.example.odainterviewcase.common.data.model.OdaInterviewCaseItemAttributes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.odainterviewcase.R

@Composable
fun ProductListItem(productAttributes: OdaInterviewCaseItemAttributes) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp).zIndex(1f),
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
                productAttributes.full_name?.let { Text(text = it, color = Color.Black, style= MaterialTheme.typography.titleSmall) }
                Text(text = "${productAttributes.gross_price} ${productAttributes.currency}")
                Text(text = "${productAttributes.gross_unit_price}/${productAttributes.unit_price_quantity_abbreviation}")
            }
            if (productAttributes.availability?.is_available==true) {
                Text(
                    text = stringResource(R.string.available_str),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.Black,
                    style= MaterialTheme.typography.titleSmall
                )
            } else {
                Text(
                    text = stringResource(R.string.not_available_str),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.Black,
                    style= MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
