package com.ephirium.lifestylehub.database.placeinfo

import com.ephirium.lifestylehub.database.placeinfo.model.PlaceInfoEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

internal object PlaceInfoDatabase {
    private val configuration by lazy {
        RealmConfiguration.create(
            schema = setOf(
                PlaceInfoEntity::class
            )
        )
    }
    
    val realm by lazy {
        Realm.open(configuration)
    }
}