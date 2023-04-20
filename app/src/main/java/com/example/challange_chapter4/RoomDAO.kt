package com.example.challange_chapter4

import androidx.room.*
import com.example.challange_chapter4.RoomData

@Dao
interface RoomDAO {
    @Insert
    fun insertData(data: RoomData)
    @Query("SELECT * FROM RoomData ORDER BY judul ASC")
    fun getDataAsc(): List<RoomData>
    @Query("SELECT * FROM RoomData ORDER BY judul DESC")
    fun getDataDesc(): List<RoomData>

    @Delete
    fun deleteData(data: RoomData)

    @Update
    fun updateData(data: RoomData)
}