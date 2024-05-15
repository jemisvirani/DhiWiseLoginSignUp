package com.jemissapplication.app.modules.signup.roomdatabase

import androidx.lifecycle.LiveData

class TokenRepository(private val tokenDao: TokenDao) {

    val allToken: LiveData<List<Token>> = tokenDao.getTokenData()

    suspend fun insert(token: Token) {
        tokenDao.insert(token)
    }

    suspend fun delete(token: Token) {
        tokenDao.delete(token)
    }

}