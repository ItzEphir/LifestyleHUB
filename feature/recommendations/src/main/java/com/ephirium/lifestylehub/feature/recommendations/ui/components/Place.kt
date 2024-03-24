package com.ephirium.lifestylehub.feature.recommendations.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ephirium.lifestylehub.androidBase.composables.Shimmer
import com.ephirium.lifestylehub.feature.recommendations.presentation.model.PlaceUiModel

@Composable
fun Place(placeUiModel: PlaceUiModel, modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    
    val photoUrls by remember {
        derivedStateOf { placeUiModel.photoUrls }
    }
    
    Card(
        modifier = modifier.clickable { onClick(placeUiModel.id) },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primaryContainer)
        )
    ) {
        when (photoUrls) {
            null -> Shimmer(
                modifier = modifier
                    .height(128.dp)
                    .padding(vertical = 8.dp)
            )
            
            else -> LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(items = photoUrls!!) {
                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = Modifier
                            .width(128.dp)
                            .height(128.dp)
                            .padding(vertical = 8.dp),
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }
        }
        
        Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
            Text(text = placeUiModel.name, modifier = Modifier.padding(4.dp))
            if (placeUiModel.address != "") Text(
                text = placeUiModel.address, modifier = Modifier.padding(4.dp)
            )
            LazyRow(modifier = modifier) {
                items(placeUiModel.categories) {
                    Text(text = it, modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlacePreview() {
    Place(placeUiModel = PlaceUiModel(
        id = "",
        name = "Red Square",
        address = "",
        categories = listOf("Avenue", "Place", "Cool"),
        photoUrls = null
    ), modifier = Modifier.fillMaxWidth(), onClick = {})
    
}