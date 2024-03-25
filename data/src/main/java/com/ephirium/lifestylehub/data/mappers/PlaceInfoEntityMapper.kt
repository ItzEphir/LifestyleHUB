package com.ephirium.lifestylehub.data.mappers

import com.ephirium.lifestylehub.database.placeinfo.model.PlaceInfoEntity
import com.ephirium.lifestylehub.domain.model.placeInfo.Hours
import com.ephirium.lifestylehub.domain.model.placeInfo.PlaceInfo
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList

fun PlaceInfoEntity.toPlaceInfo(): PlaceInfo = PlaceInfo(
    id = _id,
    name = name,
    address = address,
    categories = categories.toList(),
    dateClosed = dateClosed,
    description = description,
    email = email,
    link = website,
    hours = hours.map { it.toHours() }.toList(),
    photos = photos.toList(),
    languageCode = languageCode,
    telephone = telephone,
)

fun PlaceInfo.toEntity() = PlaceInfoEntity().apply {
    this._id = id
    this.name = this@toEntity.name
    address = this@toEntity.address
    categories = this@toEntity.categories.toRealmList()
    dateClosed = this@toEntity.dateClosed
    description = this@toEntity.description
    email = this@toEntity.email
    website = this@toEntity.link
    hours = this@toEntity.hours?.map { it.toEntity() }?.toRealmList() ?: realmListOf()
    photos = this@toEntity.photos.toRealmList()
    languageCode = this@toEntity.languageCode
}

private fun com.ephirium.lifestylehub.database.placeinfo.model.Hours.toHours(): Hours = Hours(
    open = open,
    close = close,
    day = day,
)

private fun Hours.toEntity() = com.ephirium.lifestylehub.database.placeinfo.model.Hours().apply {
    open = this@toEntity.open
    close = this@toEntity.close
    day = this@toEntity.day
}