package com.example.lircaymarket.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lircaymarket.entity.Movement
import com.example.lircaymarket.entity.Pantry

@Dao
interface MovementDao {
    @Query("SELECT * FROM movement")
    fun getAll(): List<Movement>

    @Query("SELECT * FROM movement WHERE movementid IN (:movementids)")
    fun loadAllByIds(movementids: IntArray): List<Movement>

    @Query("SELECT * FROM movement WHERE movementid = :movementids")
    fun getMovementByID(movementids: Int): Movement?

    @Query("SELECT * FROM movement WHERE user_id = :userid")
    fun getMovementsByUserId(userid: Int): List<Movement>

    @Insert
    fun insertAll(vararg movement: Movement)

    @Delete
    fun delete(movement: Movement)
}