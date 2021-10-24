package com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.dao.SchoolDao
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.Director
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.School
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.Student
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.Subject
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.relations.StudentSubjectCrossRef

@Database(
    entities = [
        School::class,
        Director::class,
        Student::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase(){
    abstract val schoolDao: SchoolDao

    companion object{
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getInstance(context: Context) : SchoolDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}