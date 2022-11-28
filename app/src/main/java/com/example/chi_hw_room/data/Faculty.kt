package com.example.chi_hw_room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Faculty(
    @PrimaryKey val id: Int,
    var name: String,
    var shortName: String
)
