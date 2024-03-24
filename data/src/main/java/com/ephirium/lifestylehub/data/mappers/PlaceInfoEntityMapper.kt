package com.ephirium.lifestylehub.data.mappers

import com.ephirium.lifestylehub.database.placeinfo.model.PlaceInfoEntity
import com.ephirium.lifestylehub.domain.model.placeInfo.Hours
import com.ephirium.lifestylehub.domain.model.placeInfo.PlaceInfo

fun PlaceInfoEntity.toPlaceInfo(): PlaceInfo = PlaceInfo(
    id = _id,
    name = name,
    address = address,
    categories = categories,
    dateClosed = dateClosed,
    description = description,
    email = email,
    link = link,
    hours = hours?.map { it.toHours() },
    photos = photos,
    languageCode = languageCode,
)

fun PlaceInfo.toEntity() = PlaceInfoEntity(
    _id = id,
    name = name,
    address = address,
    categories = categories,
    dateClosed = dateClosed,
    description = description,
    email = email,
    link = link,
    hours = hours?.map { it.toEntity() },
    photos = photos,
    languageCode = languageCode,
)

private fun com.ephirium.lifestylehub.database.placeinfo.model.Hours.toHours(): Hours = Hours(
    open = open,
    close = close,
    day = day,
)

private fun Hours.toEntity() = com.ephirium.lifestylehub.database.placeinfo.model.Hours(
    open = open,
    close = close,
    day = day,
)