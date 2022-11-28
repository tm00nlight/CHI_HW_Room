package com.example.chi_hw_room.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = Specialization::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idSpecialization")
    )]
)
data class Student(
    @PrimaryKey val id: Int,
    val firstName: String,
    var surname: String,
    val idSpecialization: Int,
    var course: Int,
    var groupNum: Int,
    val dateIn: Long? = null // Added in version 2
)
