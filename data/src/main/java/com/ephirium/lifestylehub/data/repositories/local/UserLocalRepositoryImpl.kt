package com.ephirium.lifestylehub.data.repositories.local

import com.ephirium.lifestylehub.auth.getPasswordToken
import com.ephirium.lifestylehub.data.mappers.toEntity
import com.ephirium.lifestylehub.data.mappers.toUser
import com.ephirium.lifestylehub.database.users.datastore.UsersDatastore
import com.ephirium.lifestylehub.domain.model.user.User
import com.ephirium.lifestylehub.domain.repositories.local.UserLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class UserLocalRepositoryImpl(private val usersDatastore: UsersDatastore) : UserLocalRepository {
    override suspend fun postUser(user: User, password: String): Flow<User?> =
        usersDatastore.postUser(user.toEntity(getPasswordToken(password))).map {
            it?.toUser()
        }.flowOn(Dispatchers.IO)
    
    override suspend fun getUser(login: String, password: String): Flow<User?> =
        usersDatastore.getUser(login, getPasswordToken(password)).map { it?.toUser() }
            .flowOn(Dispatchers.IO)
    
    override suspend fun getCurrentUser(): Flow<User?> =
        usersDatastore.getCurrentUser().map { it?.toUser() }
}