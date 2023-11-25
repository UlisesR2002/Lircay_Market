package com.example.lircaymarket.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lircaymarket.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE userid IN (:userids)")
    fun loadAllByIds(userids: IntArray): List<User>

    @Query("SELECT * FROM user WHERE userid = :userid")
    fun getUserByID(userid: Int): User?
    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): User?
    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}