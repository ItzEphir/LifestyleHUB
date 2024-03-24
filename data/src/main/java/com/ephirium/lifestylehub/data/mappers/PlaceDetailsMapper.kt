package com.ephirium.lifestylehub.data.mappers

import com.ephirium.lifestylehub.api.placedetails.dto.PlaceDetails
import com.ephirium.lifestylehub.domain.model.placeInfo.Hours
import com.ephirium.lifestylehub.domain.model.placeInfo.PlaceInfo

fun PlaceDetails.toPlaceInfo(languageCode: String) = PlaceInfo(
    id = id,
    name = name,
    languageCode = languageCode,
    address = address,
    categories = categories,
    dateClosed = dateClosed,
    description = description,
    email = email,
    hours = hours?.map { it.toHours() },
    link = link,
    photos = photos,
)

private fun com.ephirium.lifestylehub.api.placedetails.dto.placeDetailsReponse.Hours.toHours() =
    Hours(
        open = open,
        close = close,
        day = day,
    )