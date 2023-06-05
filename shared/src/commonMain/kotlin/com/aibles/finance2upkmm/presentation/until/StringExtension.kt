package com.aibles.finance2upkmm.presentation.until


fun String.isValidUsername(): Boolean {
    val usernamePattern = "^[A-Za-z0-9]+\$"
    val pattern = Regex(usernamePattern)
    return this.isNotEmpty() && pattern.matches(this)
}

fun String.isValidFullName(): Boolean {
    val usernamePattern = "^[A-Za-z][a-z]*( [A-Za-z][a-z]*){2}\$"
    val pattern = Regex(usernamePattern)
    return this.isNotEmpty() && pattern.matches(this)
}

fun String.isValidPassword(): Boolean {
    val passwordPattern = "^(?=.{8,16})(?=.*[a-z])(?=.*[A-Z])(?=.*[?!@#\$%^&*+=._()-]).*\$"
    val pattern = Regex(passwordPattern)
    return this.isNotEmpty() && pattern.matches(this)
}

fun String.isValidEmail(): Boolean {
    val emailPattern = "^[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+\$"
    val pattern = Regex(emailPattern)
    return this.isNotEmpty() && pattern.matches(this)
}