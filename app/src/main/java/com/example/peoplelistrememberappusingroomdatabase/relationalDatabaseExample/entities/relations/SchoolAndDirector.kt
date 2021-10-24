package com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.Director
import com.example.peoplelistrememberappusingroomdatabase.relationalDatabaseExample.entities.School

data class SchoolAndDirector (
    @Embedded
    val school: School,
    @Relation(
        parentColumn = "schoolName", // here we are saying that schoolName field which is present in school, use that bitch for storing the data
        entityColumn = "schoolName" // and here the bitch, i mean field by the same name, will be connected to that parent field.
    )
    val director: Director
    )
//to tell the room and these two class's or entities have a relation (one to one) together we need to define this helper class,

// so how the hell does it work and wtf is this all?
// ans: we have two entities director and school and we want to created one to one relations between those two, what do i mean by that is
// director have a one field by the name of schoolName and school entity also needs to have a director fields as well, so instead of doubling
// the data we can create a relation between them. so here parentColumn and entityColumn will be present in both the tables but will be present
// only in one table, but since these two table are connected by one to one relation, that field will be present in second fields as well, thus
// saving us from creating the dublicate data.
// and by doing all this we can also refer one entity field with another, example using only schoolName we can know the director which is linked with it in the other table
// and this mf thing makes it amazing