package com.example.chi_hw_room

import androidx.lifecycle.ViewModel
import com.example.chi_hw_room.data.Faculty
import kotlinx.coroutines.CoroutineScope

class MainViewModel(app: UniversityApplication) : ViewModel() {
    private val dao = app.database.universityDao
    private val daoRel = app.database.facultyWithSpecializationsDao

    suspend fun addFaculties(list: List<Faculty>) = dao.addFaculties(list)

    suspend fun getStudentsByCourse(course: Int) = dao.getStudentsByCourse(course)

    fun getStudents() = dao.getStudents()

    suspend fun getSpecializationName(id: Int) = dao.getSpecializationName(id)

    fun getSubjects() = dao.getSubjects()

    suspend fun updateStudentSurname(id: Int, surname: String) = dao.updateSurnameWhenMarried(id, surname)

    fun getFacultiesWithSpecs() = daoRel.getFacultiesWithSpecs()
}