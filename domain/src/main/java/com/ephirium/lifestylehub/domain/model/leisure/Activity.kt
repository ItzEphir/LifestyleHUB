package com.ephirium.lifestylehub.domain.model.leisure

data class Activity(
    val id: String,
    val userLogin: String,
    val name: String,
    val details: List<ActivityDetail>,
    val notes: List<String>,
)