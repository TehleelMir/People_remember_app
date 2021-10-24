package com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.dao

import androidx.room.*
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.Director
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.School
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.Student
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.Subject
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.relations.*

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // this means if the object of same data is already present then, just replace that mf
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(croosRef: StudentSubjectCrossRef)

    @Transaction // this won't let any mf thread to interrupt the current one, we needed to do this,
    // because to are playing with two tables at the same time, and somewhere any other operation might be trying to access one of the tables and can manuplute the data
    @Query("SELECT * FROM School WHERE schoolName = :schoolName") // here we use : so that to refer to the parameter
    suspend fun getSchoolAndDirectorBySchoolName(schoolName: String) : List<SchoolAndDirector>

    @Transaction
    @Query("SELECT * FROM School WHERE schoolName = :schoolName")
    suspend fun getSchoolWithStudentsBySchoolName(schoolName: String) : List<SchoolWithStudent>

    @Transaction
    @Query("SELECT * FROM Subject WHERE subjectName = :subjectName")
    suspend fun getStudentsOfSubject(subjectName: String) : List<SubjectWithStudents>

    @Transaction
    @Query("SELECT * FROM Student WHERE studentName = :studentName")
    suspend fun getSubjectOfStudent(studentName: String) : List<StudentsWithSubject>
}