package com.example.chi_hw_room.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Faculty::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idFaculty")
    )]
)
data class Specialization(
    @PrimaryKey val id: Int,
    var name: String,
    var idFaculty: Int
)
