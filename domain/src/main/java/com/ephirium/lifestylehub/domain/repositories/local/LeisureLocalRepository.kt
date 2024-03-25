package com.ephirium.lifestylehub.domain.repositories.local

import com.ephirium.lifestylehub.domain.model.leisure.Activity
import kotlinx.coroutines.flow.Flow

interface LeisureLocalRepository {
    suspend fun getLeisure(userLogin: String): Flow<List<Activity>>
    
    suspend fun postLeisure(activity: Activity): Flow<Unit>
}