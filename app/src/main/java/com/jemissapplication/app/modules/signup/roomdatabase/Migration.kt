package com.jemissapplication.app.modules.signup.roomdatabase

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_7_8 = object : Migration(7,8){
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE token_table ADD COLUMN age TEXT NOT NULL DEFAULT ''")
    }
}
val MIGRATION_8_9 = object : Migration(8,9){
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE token_table RENAME COLUMN age TO current_age")
    }
}

val MIGRATION_9_10 = object : Migration(9,10){
    override fun migrate(db: SupportSQLiteDatabase) {
     db.execSQL("ALTER TABLE token_table ***DROP*** COLUMN current_age")
    }
}

