package com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class School(
    @PrimaryKey(autoGenerate = false)
    val schoolName: String
)

//this class will have one to one relation with Director entity class