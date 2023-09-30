package com.example.royxatdanotish.db

import com.example.royxatdanotish.Mudels.User

interface DbHelper {
    fun getAll():ArrayList<User>
    fun addAll(user:User)
}