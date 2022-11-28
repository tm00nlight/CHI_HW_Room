package com.example.chi_hw_room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey val id: Int,
    var name: String
)
