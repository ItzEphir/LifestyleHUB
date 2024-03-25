package com.ephirium.lifestylehub.data.repositories.local

import com.ephirium.lifestylehub.data.mappers.toActivity
import com.ephirium.lifestylehub.data.mappers.toEntity
import com.ephirium.lifestylehub.database.leisure.datastore.LeisureDatastore
import com.ephirium.lifestylehub.domain.model.leisure.Activity
import com.ephirium.lifestylehub.domain.repositories.local.LeisureLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LeisureLocalRepositoryImpl(private val leisureDatastore: LeisureDatastore) :
    LeisureLocalRepository {
    override suspend fun getLeisure(userLogin: String): Flow<List<Activity>> =
        leisureDatastore.getLeisure(userLogin).map { entities ->
            entities.map { entity -> entity.toActivity() }
        }
    
    override suspend fun postLeisure(activity: Activity): Flow<Unit> =
        leisureDatastore.postLeisure(activity.toEntity())
}