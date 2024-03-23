package com.ephirium.lifestylehub.domain.model.recommendation

data class Place(
    override val id: String,
    val name: String,
    val address: String,
    val categories: List<Category>,
    val placePhotoUrls: List<String>?,
    override val cursor: String?,
    override val nextCursor: String?,
) : Recommendation {
    data class Category(val name: String, val pluralName: String)
}