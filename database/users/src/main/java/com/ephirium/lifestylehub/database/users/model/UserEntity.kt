package com.ephirium.lifestylehub.database.users.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class UserEntity: RealmObject {
    @PrimaryKey
    var login: String = ""
    var passwordToken: String = ""
    var avatarUrl: String = ""
    var name: String = ""
    var current: Boolean = false
}