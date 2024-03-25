package com.ephirium.lifestylehub.api.placedetails.mapper

import com.ephirium.lifestylehub.api.placedetails.dto.PlaceDetails
import com.ephirium.lifestylehub.api.placedetails.dto.placeDetailsReponse.PlaceDetailsResponse

fun PlaceDetailsResponse.toPlaceDetails() = PlaceDetails(
    id = id,
    name = name,
    description = description ?: "",
    address = location.address,
    categories = categories.map { it.name },
    email = email,
    telephone = tel,
    dateClosed = dateClosed,
    hours = hours,
    website = website,
    photos = photos.map { it.prefix + "original" + it.suffix },
)