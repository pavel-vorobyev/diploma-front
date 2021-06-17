package com.pavelvorobyev.diploma.util.extensions

val String.Companion.Empty: String
    get() = String().intern()
