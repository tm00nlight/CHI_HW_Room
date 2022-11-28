package com.example.chi_hw_room.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Specialization::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("idSpecialization")
        ), ForeignKey(
            entity = Subject::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("idSubject")
        )
    ]
)
data class Program(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val idSpecialization: Int,
    val idSubject: Int,
    var hours: Int
)
