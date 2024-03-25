package com.ephirium.lifestylehub.auth

fun getPasswordToken(password: String) = password.toHashSet().fold("") { acc, c -> acc + c }