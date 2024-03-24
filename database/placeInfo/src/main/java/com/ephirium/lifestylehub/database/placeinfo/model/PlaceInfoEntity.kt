package com.ephirium.lifestylehub.database.placeinfo.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

data class PlaceInfoEntity(
    @Suppress("PropertyName") @PrimaryKey val _id: String,
    val name: String,
    val languageCode: String,
    val categories: List<String>,
    val dateClosed: String?,
    val description: String,
    val email: String?,
    val link: String?,
    val address: String,
    val hours: List<Hours>?,
    val photos: List<String>,
) : RealmObject
