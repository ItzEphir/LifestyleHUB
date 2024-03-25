package com.ephirium.lifestylehub.data.mappers

import com.ephirium.lifestylehub.database.users.model.UserEntity
import com.ephirium.lifestylehub.domain.model.user.User

fun UserEntity.toUser() = User(
    login = login,
    name = name,
    avatarUrl = avatarUrl,
)

fun User.toEntity(passwordToken: String) = UserEntity().also{
    it.login = login
    it.name = name
    it.avatarUrl = avatarUrl
    it.current = true
    it.passwordToken = passwordToken
}