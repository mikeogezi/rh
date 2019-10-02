package com.example.reliancehmo.models

open class Response {

    lateinit var status: String

    fun isSuccess (): Boolean {
        return "success" == status
    }

}