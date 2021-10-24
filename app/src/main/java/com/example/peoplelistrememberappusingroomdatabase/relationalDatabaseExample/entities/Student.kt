package com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = false)
    val studentName: String,
    val semester: Int,
    val schoolName: String
)

// this entity will have a one to n relations with school entity, since one student can be part of only one school but one school
// can have more then one students so thus presenting one to n relations,