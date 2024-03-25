package com.ephirium.lifestylehub.database.leisure.datastore

import com.ephirium.lifestylehub.database.leisure.model.ActivityEntity
import kotlinx.coroutines.flow.Flow

interface LeisureDatastore {
    suspend fun getLeisure(userLogin: String): Flow<List<ActivityEntity>>
    
    suspend fun postLeisure(activityEntity: ActivityEntity): Flow<Unit>
}