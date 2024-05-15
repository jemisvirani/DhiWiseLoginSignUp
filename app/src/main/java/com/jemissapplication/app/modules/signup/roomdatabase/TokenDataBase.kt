package com.jemissapplication.app.modules.signup.roomdatabase

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [Token::class],
    version = 10,
    autoMigrations = [AutoMigration(from = 1, to = 2), AutoMigration(
        from = 2,
        to = 3,
        spec = TokenDataBase.Migration1To2::class
    ), AutoMigration(from = 3, to = 4), AutoMigration(from = 4, to = 5,spec = TokenDataBase.DeleteMigration::class), AutoMigration(
        from = 5,
        to = 6,
        spec = TokenDataBase.DeleteMigration::class
    )]
)
abstract class TokenDataBase : RoomDatabase() {
    abstract fun getTokenDao(): TokenDao

    @RenameColumn(tableName = "token_table", fromColumnName = "Token", toColumnName = "token")
    class Migration1To2 : AutoMigrationSpec

    @DeleteColumn(tableName = "token_table", columnName = "age")
    class DeleteMigration : AutoMigrationSpec
    companion object {


        @Volatile
        private var INSTANCE: TokenDataBase? = null

        fun getInstanceData(context: Context): TokenDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TokenDataBase::class.java,
                    "token_database"
                ).addMigrations(MIGRATION_7_8,MIGRATION_8_9,MIGRATION_9_10).build()

                INSTANCE = instance
                instance
            }
        }

        //Major Disadvantages of FallBackToDestructive Migration
        //Data destroy kari de // data ne wipe out kari de che
//        fun getInstanceData(context: Context): TokenDataBase {
//
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TokenDataBase::class.java,
//                    "token_database"
//                ).fallbackToDestructiveMigration().build()
//
//                INSTANCE = instance
//                instance
//            }
//        }

    }


}