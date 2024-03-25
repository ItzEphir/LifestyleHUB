package com.ephirium.lifestylehub.database.placeinfo.model

import io.realm.kotlin.types.EmbeddedRealmObject

class Hours: EmbeddedRealmObject {
    var close: String = ""
    var day: Int = 0
    var open: String = ""
}
