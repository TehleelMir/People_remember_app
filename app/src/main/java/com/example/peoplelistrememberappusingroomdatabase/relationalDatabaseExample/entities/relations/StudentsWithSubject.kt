package com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.Student
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.Subject

data class StudentsWithSubject(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<Subject>
)