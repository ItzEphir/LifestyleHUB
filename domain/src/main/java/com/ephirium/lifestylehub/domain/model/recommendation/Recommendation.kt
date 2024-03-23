package com.ephirium.lifestylehub.domain.model.recommendation

sealed interface Recommendation {
    val id: String
    val cursor: String?
    val nextCursor: String?
}