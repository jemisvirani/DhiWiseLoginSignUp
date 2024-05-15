package com.jemissapplication.app.modules.signup.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token_table")
class Token(@ColumnInfo(name = "token") val token : String,@ColumnInfo(name = "username") val  username : String,@ColumnInfo("password") val password : String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}
//@Entity(tableName = "token_table")
//class Token(@ColumnInfo(name = "token") val token : String,@ColumnInfo(name = "username") val  username : String,@ColumnInfo("password") val password : String,@ColumnInfo("current_age", defaultValue = "") val age : String) {
//    @PrimaryKey(autoGenerate = true) var id = 0
//}
//@Entity(tableName = "token_table")
//class Token(@ColumnInfo(name = "token") val token : String,@ColumnInfo(name = "username") val  username : String,@ColumnInfo(name = "age", defaultValue = "22") val age : Int) {
//    @PrimaryKey(autoGenerate = true) var id = 0
//}
