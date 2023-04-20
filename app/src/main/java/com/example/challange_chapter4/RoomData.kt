package com.example.challange_chapter4

import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity
data class RoomData (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var judul:String,
    var content:String
):java.io.Serializable