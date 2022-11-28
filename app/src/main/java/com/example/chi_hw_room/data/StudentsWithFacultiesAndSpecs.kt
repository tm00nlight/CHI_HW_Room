package com.example.chi_hw_room.data

import androidx.room.DatabaseView

@DatabaseView(
    "select firstName, surname, Specialization.name, Faculty.name, course, groupNum " +
            "from Student inner join Specialization on Student.idSpecialization = Specialization.id " +
            "inner join Faculty on Faculty.id = Specialization.idFaculty"
)
data class StudentsWithFacultiesAndSpecs(
    val firstName: String,
    var surname: String,
    val specialization: String,
    val faculty: String,
    var course: Int,
    var groupNum: Int
)
