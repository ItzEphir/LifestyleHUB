package com.ephirium.lifestylehub.feature.placedetails.presentation.mapper

import com.ephirium.lifestylehub.domain.model.placeInfo.Hours
import com.ephirium.lifestylehub.domain.model.placeInfo.PlaceInfo
import com.ephirium.lifestylehub.feature.placedetails.presentation.model.PlaceUiModel

fun PlaceInfo.toPlaceUi() = PlaceUiModel(
    id = id,
    name = name,
    categories = categories,
    address = address,
    dateClosed = dateClosed,
    description = description,
    email = email,
    hours = hours?.map { it.toUi() },
    website = link,
    photos = photos,
    telephone = telephone
)

private fun Hours.toUi() = PlaceUiModel.Hours(
    open = open,
    close = close,
    day = day,
)