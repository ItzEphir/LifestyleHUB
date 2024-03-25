package com.ephirium.lifestylehub.database.users.datastore

import com.ephirium.lifestylehub.database.users.UsersDatabase
import com.ephirium.lifestylehub.database.users.model.UserEntity
import io.realm.kotlin.UpdatePolicy.ALL
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsersDatastoreImpl : UsersDatastore {
    override suspend fun postUser(user: UserEntity): Flow<UserEntity?> = UsersDatabase.realm.write {
        copyToRealm(user, ALL)
    }.asFlow().map { it.obj }
    
    override suspend fun getUser(login: String, passwordToken: String): Flow<UserEntity?> =
        UsersDatabase.realm.query<UserEntity>().asFlow().map { resultsChange ->
            resultsChange.list.firstOrNull { user -> user.current && user.login == login && user.passwordToken == passwordToken }
        }
    
    override suspend fun getCurrentUser(): Flow<UserEntity?> = UsersDatabase.realm.query<UserEntity>().asFlow().map { resultsChange ->
        resultsChange.list.firstOrNull{user -> user.current}
    }
}