package com.jemissapplication.app.modules.signup.roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TokenViewModel(application: Application) : AndroidViewModel(application) {

    private val tokenRepository: TokenRepository
     var allToken: LiveData<List<Token>>

    init {
        val dataBaseToken =
            TokenDataBase.getInstanceData(application.applicationContext).getTokenDao()
        tokenRepository = TokenRepository(dataBaseToken)
        allToken = tokenRepository.allToken
    }

    fun insert(token: Token) = viewModelScope.launch(Dispatchers.IO) {
        tokenRepository.insert(token)
    }

    fun delete(token: Token) = viewModelScope.launch(Dispatchers.IO) {
        tokenRepository.delete(token)
    }
}