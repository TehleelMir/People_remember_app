package com.example.peoplelistrememberappusingroomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // this means that if there is already same user object present in the db then just ignore
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    // or we can also use @Delete which will delete user, but we have to pass user object then, innstad of id
    // and to delte all the user we can useing the following query DELETE FROM user_table and thats all.
    @Query("DELETE FROM user_table WHERE id = :userId")
    suspend fun deleteUser(userId: Int)
}