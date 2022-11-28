package com.example.chi_hw_room

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chi_hw_room.data.Faculty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = application as UniversityApplication
        val viewModel = MainViewModel(app)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.addFaculties(
                listOf(
                    Faculty(5, "Факультет міжнародної торгівлі", "ФМТ")
                )
            )
            viewModel.getFacultiesWithSpecs().forEach {
                println(it.faculty?.name + " - " + it.specs.toString())
            }

            for (s in viewModel.getStudentsByCourse(1)) {
                println(viewModel.getSpecializationName(s.idSpecialization) + " - " +
                        s.firstName + " " + s.surname)
            }

            viewModel.getSubjects().collect {
                println("Всього ${it.size} предметів")
            }

            viewModel.updateStudentSurname(3, "Микитенко")
            viewModel.getStudents().last().filter { it.id == 3 }.forEach { println(it.surname) }
        }
    }
}