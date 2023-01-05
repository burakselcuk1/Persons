package com.example.persons.repository

import com.example.persons.common.handleRequestFlow
import com.example.persons.services.ApiImpl
import javax.inject.Inject


class UserRepository @Inject constructor(private val usersApiImple: ApiImpl) {

    suspend fun getUsers() =  handleRequestFlow { usersApiImple.getUsers() }
    suspend fun getPosts(id: Int) = handleRequestFlow {usersApiImple.getPosts(id)}

}