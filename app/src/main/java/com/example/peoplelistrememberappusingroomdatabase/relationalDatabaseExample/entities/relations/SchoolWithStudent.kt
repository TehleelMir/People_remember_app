package com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.School
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.Student

data class SchoolWithStudent(
    @Embedded
    val school: School, // here the major table must be the one which contains multiple instances of other table, school contains more then 1 student
    @Relation(
        parentColumn = "schoolName", // this must be the primary key, which will be both common in both tables and this field will join both the tables
        // , schoolName is the primary key of school table,
        entityColumn = "schoolName" // and later on this relation will use this common field to create a relation between these two tables, here schoolName is the field of student table
    )
    val students: List<Student>
)