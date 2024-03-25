package com.ephirium.lifestylehub.database.leisure.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ActivityEntity: RealmObject {
    @Suppress("PropertyName")
    @PrimaryKey
    var _id: String = ""
    var userLogin: String = ""
    var name: String = ""
    var details: RealmList<ActivityDetailEntity> = realmListOf()
    var notes: RealmList<String> = realmListOf()
}