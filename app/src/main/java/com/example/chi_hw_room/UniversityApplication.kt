package com.example.chi_hw_room

import android.app.Application
import com.example.chi_hw_room.db.UniversityDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class UniversityApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database: UniversityDataBase by lazy { UniversityDataBase.getDataBase(this, applicationScope) }
}