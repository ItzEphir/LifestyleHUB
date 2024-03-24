package com.ephirium.lifestylehub.feature.placedetails.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ephirium.lifestylehub.feature.placedetails.presentation.model.PlaceUiModel
import com.ephirium.lifestylehub.feature.placedetails.presentation.model.PlaceUiModel.Hours
import com.ephirium.lifestylehub.feature.placedetails.ui.screen.PlaceDetailsScreen

@Composable
fun PlaceDetails(placeUiModel: PlaceUiModel) {

    Column(modifier = Modifier.s){
    
    }

}

@Preview(showBackground = true)
@Composable
private fun PlaceDetailsPreview() {
    
    PlaceDetails(
        PlaceUiModel(
            id = "",
            name ="Red Square",
            categories = listOf("Square"),
            dateClosed = null,
            description = "Amazing Square Blablablablablablablablablablablablabla",
            address = "Moscow",
            email = null,
            hours = listOf(Hours("10:00", "21:00", 5)),
            link = null,
            photos = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Red_square_Moscow_cityscape_%288309148721%29.jpg/1920px-Red_square_Moscow_cityscape_%288309148721%29.jpg")
        )
    )
    
}