package com.pavelvorobyev.diploma.extensions

val String.Companion.Empty: String
    get() = String().intern()
