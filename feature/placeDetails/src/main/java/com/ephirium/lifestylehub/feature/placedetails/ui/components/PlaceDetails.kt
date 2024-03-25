package com.ephirium.lifestylehub.feature.placedetails.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ephirium.lifestylehub.feature.placedetails.R.string
import com.ephirium.lifestylehub.feature.placedetails.presentation.model.PlaceUiModel
import com.ephirium.lifestylehub.feature.placedetails.presentation.model.PlaceUiModel.Hours

@Composable
fun PlaceDetails(
    placeUiModel: PlaceUiModel,
    modifier: Modifier = Modifier,
    onAddLeisureClick: () -> Unit,
) {
    println(placeUiModel.photos)
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        item {
            LazyRow {
                items(items = placeUiModel.photos) { url ->
                    AsyncImage(
                        model = url,
                        contentDescription = null,
                        modifier = Modifier
                            .width(128.dp)
                            .height(128.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Text(
                text = placeUiModel.name,
                style = MaterialTheme.typography.displayLarge,
            )
            Text(
                text = placeUiModel.address,
                style = MaterialTheme.typography.headlineSmall,
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                items(items = placeUiModel.categories) { category ->
                    Text(
                        text = category,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
            Text(
                text = placeUiModel.description,
                style = MaterialTheme.typography.titleMedium,
            )
            placeUiModel.website?.let { website ->
                Text(
                    text = website,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            if ((placeUiModel.email != null) or (placeUiModel.telephone != null)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    placeUiModel.email?.let { email ->
                        Text(
                            text = email,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                    placeUiModel.telephone?.let { telephone ->
                        Text(
                            text = telephone,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                }
            }
            placeUiModel.dateClosed?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            
            placeUiModel.hours?.let {
                Column {
                    it.forEach { hours ->
                        Text(
                            text = "${hours.day}: ${hours.open} - ${hours.close}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
        item {
            Button(onClick = onAddLeisureClick, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(string.add_to_leisure_button),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaceDetailsPreview() {
    
    PlaceDetails(PlaceUiModel(
        id = "",
        name = "Red Square",
        categories = listOf("Square"),
        dateClosed = null,
        description = "Amazing Square",
        address = "Moscow",
        email = "a@m.ru",
        telephone = "+79999999999",
        hours = listOf(Hours("10:00", "21:00", 5)),
        website = "https://google.com",
        photos = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Red_square_Moscow_cityscape_%288309148721%29.jpg/1920px-Red_square_Moscow_cityscape_%288309148721%29.jpg")
    ), onAddLeisureClick = {})
    
}