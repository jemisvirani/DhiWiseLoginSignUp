package com.jemissapplication.app.modules.signup.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(token: Token)

    @Delete
    suspend fun delete(token: Token)

    @Query("Select * from token_table order by id ASC")
    fun getTokenData() : LiveData<List<Token>>
}