package com.ephirium.lifestylehub.database.users

import com.ephirium.lifestylehub.database.users.model.UserEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

internal object UsersDatabase {
    private val configuration by lazy {
        RealmConfiguration.create(
            schema = setOf(
                UserEntity::class
            )
        )
    }
    
    val realm by lazy{
        Realm.open(configuration)
    }
}