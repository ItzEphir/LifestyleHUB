package com.ephirium.lifestylehub.database.users.datastore

import com.ephirium.lifestylehub.database.users.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UsersDatastore {
    suspend fun postUser(user: UserEntity) : Flow<UserEntity?>
    
    suspend fun getUser(login: String, passwordToken: String) : Flow<UserEntity?>
    
    suspend fun getCurrentUser(): Flow<UserEntity?>
}