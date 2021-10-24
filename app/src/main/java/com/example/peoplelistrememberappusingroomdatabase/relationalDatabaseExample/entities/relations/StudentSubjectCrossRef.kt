package com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["studentName", "subjectName"])
data class StudentSubjectCrossRef(
    val studentName: String,
    val subjectName: String
)

//https://www.youtube.com/watch?v=AHn5JQVlJJM&list=TLPQMjQxMDIwMjF4UCg2FdiWAg&index=2