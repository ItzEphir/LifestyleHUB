package com.ephirium.lifestylehub.database.leisure.datastore

import com.ephirium.lifestylehub.database.leisure.LeisureDatabase
import com.ephirium.lifestylehub.database.leisure.model.ActivityEntity
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class LeisureDatastoreImpl : LeisureDatastore {
    override suspend fun getLeisure(userLogin: String): Flow<List<ActivityEntity>> =
        LeisureDatabase.realm.query<ActivityEntity>().asFlow().map { resultsChange ->
            resultsChange.list.filter { entity -> entity.userLogin == userLogin }
        }.flowOn(Dispatchers.IO)
    
    override suspend fun postLeisure(activityEntity: ActivityEntity): Flow<Unit> =
        LeisureDatabase.realm.write {
            copyToRealm(activityEntity)
        }.asFlow().map { }.flowOn(Dispatchers.IO)
    
}