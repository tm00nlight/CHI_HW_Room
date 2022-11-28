package com.example.chi_hw_room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.chi_hw_room.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UniversityDao {
    /**
     * Method for adding faculties
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFaculties(list: List<Faculty>)

    /**
     * Method for adding specializations
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSpecializations(list: List<Specialization>)

    /**
     * Method for adding subjects
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubjects(list: List<Subject>)

    /**
     * Method for adding educational programs
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPrograms(list: List<Program>)

    /**
     * Method for adding students
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudents(list: List<Student>)

    /**
     * Method for clearing students table
     */
    @Query("delete from Student")
    suspend fun clearStudents()

    /**
     * Method for deleting student with exact id
     */
    @Query("delete from Student where id = :id")
    suspend fun deleteStudent(id: Int)

    /**
     * Method for getting specialization name by id
     */
    @Query("select name from Specialization where id = :id")
    suspend fun getSpecializationName(id: Int): String

    /**
     * Method for updating student's surname, for example when married
     */
    @Query("update Student set surname = :newSurname where id = :id")
    suspend fun updateSurnameWhenMarried(id: Int, newSurname: String)

    /**
     * Method for getting list of students of exact course number via SQLite query
     */
    @RawQuery
    suspend fun getStudentsByCourse(query: SupportSQLiteQuery): List<Student>

    /**
     * Method for creating SQLite query for transaction "getStudentsByCourse"
     */
    private fun getStudentsByCourseQuery(course: Int) = SimpleSQLiteQuery(
        "select * from Student where course = $course order by idSpecialization asc"
    )

    /**
     * Method for getting list of students of exact course number
     */
    @Transaction
    suspend fun getStudentsByCourse(course: Int) = getStudentsByCourse(
        getStudentsByCourseQuery(course)
    )

    /**
     * Method for getting list of all faculties in Flow
     */
    @Query("select * from Faculty")
    fun getFaculties(): Flow<List<Faculty>>

    /**
     * Method for getting list of all educational programs in Flow
     */
    @Query("select * from Program")
    fun getPrograms(): Flow<List<Program>>

    /**
     * Method for getting list of all specializations in Flow
     */
    @Query("select * from Specialization")
    fun getSpecializations(): Flow<List<Specialization>>

    /**
     * Method for getting list of all students in Flow
     */
    @Query("select * from Student")
    fun getStudents(): Flow<List<Student>>

    /**
     * Method for getting list of all subjects in Flow
     */
    @Query("select * from Subject")
    fun getSubjects(): Flow<List<Subject>>

}