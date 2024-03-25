package com.ephirium.lifestylehub.database.placeinfo.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class PlaceInfoEntity : RealmObject {
    @Suppress("PropertyName")
    @PrimaryKey
    var _id: String = ""
    var name: String = ""
    var languageCode: String = ""
    var categories: RealmList<String> = realmListOf()
    var dateClosed: String? = null
    var description: String = ""
    var email: String? = null
    var telephone: String? = null
    var website: String? = null
    var address: String =""
    var hours: RealmList<Hours> = realmListOf()
    var photos: RealmList<String> = realmListOf()
}