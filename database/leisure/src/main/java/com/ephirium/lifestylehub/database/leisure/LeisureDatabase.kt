package com.ephirium.lifestylehub.database.leisure

import com.ephirium.lifestylehub.database.leisure.model.ActivityDetailEntity
import com.ephirium.lifestylehub.database.leisure.model.ActivityEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

internal object LeisureDatabase {
    private val configuration by lazy {
        RealmConfiguration.create(
            schema = setOf(
                ActivityEntity::class,
                ActivityDetailEntity::class
            )
        )
    }
    
    val realm by lazy {
        Realm.open(configuration)
    }
}