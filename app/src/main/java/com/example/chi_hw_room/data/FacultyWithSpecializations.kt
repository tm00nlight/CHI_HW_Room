package com.example.chi_hw_room.data

import androidx.room.Embedded
import androidx.room.Relation

class FacultyWithSpecializations {
    @Embedded
    var faculty: Faculty? = null

    @Relation(
        parentColumn = "id",
        entityColumn = "idFaculty"
    )
    var specs: List<Specialization> = ArrayList()
}
