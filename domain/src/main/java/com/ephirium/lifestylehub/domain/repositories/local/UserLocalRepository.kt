package com.ephirium.lifestylehub.domain.repositories.local

import com.ephirium.lifestylehub.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserLocalRepository {
    suspend fun postUser(user: User, password: String): Flow<User?>
    
    suspend fun getUser(login: String, password: String): Flow<User?>
    
    suspend fun getCurrentUser(): Flow<User?>
}