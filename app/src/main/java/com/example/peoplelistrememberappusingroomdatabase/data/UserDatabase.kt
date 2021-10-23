package com.example.peoplelistrememberappusingroomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false) // here entities are like the different tables which we want to store
//in our database and if we have more then one just add another one separated by ,'s
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile // this means that writes to this fields is instantly made visible to other threads
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context) : UserDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}