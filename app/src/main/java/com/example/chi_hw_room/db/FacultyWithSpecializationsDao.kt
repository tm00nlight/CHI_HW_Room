package com.example.chi_hw_room.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.chi_hw_room.data.Faculty
import com.example.chi_hw_room.data.FacultyWithSpecializations
import com.example.chi_hw_room.data.Specialization

@Dao
interface FacultyWithSpecializationsDao {
    @Query("select * from Faculty")
    fun getFaculties(): List<Faculty>

    @Query("select * from Specialization where idFaculty = :id")
    fun getSpecializationsByFaculty(id: Int): List<Specialization>

    @Transaction
    @Query("select * from Faculty")
    fun getFacultiesWithSpecs(): List<FacultyWithSpecializations>

}