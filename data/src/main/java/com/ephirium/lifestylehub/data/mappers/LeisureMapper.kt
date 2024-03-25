package com.ephirium.lifestylehub.data.mappers

import com.ephirium.lifestylehub.database.leisure.model.ActivityDetailEntity
import com.ephirium.lifestylehub.database.leisure.model.ActivityEntity
import com.ephirium.lifestylehub.domain.model.leisure.Activity
import com.ephirium.lifestylehub.domain.model.leisure.ActivityDetail
import io.realm.kotlin.ext.toRealmList

fun ActivityEntity.toActivity() = Activity(
    id = _id,
    userLogin = userLogin,
    name = name,
    details = details.map { it.toActivityDetail() },
    notes = notes.toList(),
)

fun Activity.toEntity() = ActivityEntity().also { activityEntity ->
    activityEntity._id = id
    activityEntity.userLogin = userLogin
    activityEntity.name = name
    activityEntity.details =
        details.map { activityDetail -> activityDetail.toEntity() }.toRealmList()
    activityEntity.notes = notes.toRealmList()
}

private fun ActivityDetailEntity.toActivityDetail() = ActivityDetail(
    name = name
)

private fun ActivityDetail.toEntity() = ActivityDetailEntity().also {
    it.name = name
}