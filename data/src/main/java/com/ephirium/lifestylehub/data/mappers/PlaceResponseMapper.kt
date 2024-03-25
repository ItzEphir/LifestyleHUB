package com.ephirium.lifestylehub.data.mappers

import com.ephirium.lifestylehub.api.places.dto.placeResponse.Location
import com.ephirium.lifestylehub.api.places.dto.placeResponse.PlaceDto
import com.ephirium.lifestylehub.domain.model.recommendation.Place
import com.ephirium.lifestylehub.domain.model.recommendation.Place.Category

fun PlaceDto.toPlace(nextCursor: String? = null, placePhotoUrls: List<String>?)= Place(
    id = id,
    name = name,
    address = location.toAddress(),
    placePhotoUrls = placePhotoUrls,
    categories = categories.map { it.toDomain() },
    cursor = cursor,
    nextCursor = nextCursor,
)

private fun Location.toAddress() = when (address) {
    null -> ""
    else ->
        if(addressExtended == null) "$address" else "$address, $addressExtended"
}

private fun com.ephirium.lifestylehub.api.places.dto.placeResponse.Category.toDomain() =
    Category(
        name = name, pluralName = pluralName
    )