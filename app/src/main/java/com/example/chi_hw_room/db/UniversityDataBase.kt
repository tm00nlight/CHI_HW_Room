package com.example.chi_hw_room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.chi_hw_room.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        Faculty::class,
        Program::class,
        Specialization::class,
        Student::class,
        Subject::class
    ], views = [StudentsWithFacultiesAndSpecs::class],
    version = 2, exportSchema = false
)

@TypeConverters(DateTypeConverter::class)

abstract class UniversityDataBase : RoomDatabase() {
    abstract val universityDao: UniversityDao
    abstract val facultyWithSpecializationsDao: FacultyWithSpecializationsDao

    companion object {
        private const val DB_NAME = "UniversityDB"
        private val Migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Student ADD COLUMN dateIn TEXT")
            }
        }

        @Volatile
        private var instance: UniversityDataBase? = null

        fun getDataBase(
            context: Context,
            scope: CoroutineScope
        ): UniversityDataBase {
            return instance ?: synchronized(this) {
                val newDB = Room.databaseBuilder(
                    context.applicationContext,
                    UniversityDataBase::class.java,
                    DB_NAME
                )
                    .addCallback(DataBaseCallback(scope))
                    .addMigrations(Migration_1_2)
                    .build()
                instance = newDB

                newDB
            }
        }

        private class DataBaseCallback(private val scope: CoroutineScope) :
            Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                instance?.let { database ->
                    scope.launch {
                        populateDataBase(database.universityDao)
                        db.execSQL("create trigger del_subj after delete on Subject begin delete from Program where Program.idSubject = OLD.id; end;")
                    }
                }
            }

            suspend fun populateDataBase(dao: UniversityDao) {
                dao.addFaculties(
                    listOf(
                        Faculty(1, "Факультет інформаційних технологій", "ФІТ"),
                        Faculty(2, "Факультет торгівлі та маркетингу", "ФТМ"),
                        Faculty(3, "Факультет економіки, менеджменту та права", "ФЕМП"),
                        Faculty(4, "Факультет фінансів та обліку", "ФФО")
                    )
                )

                dao.addSpecializations(
                    listOf(
                        Specialization(1, "Інженерія програмного забезпечення", 1),
                        Specialization(2, "Маркетинг та реклама", 2),
                        Specialization(3, "Міжнародне право", 3)
                    )
                )

                dao.addSubjects(
                    listOf(
                        Subject(1, "Основи програмування"),
                        Subject(2, "Математичне моделювання"),
                        Subject(3, "Психологія"),
                        Subject(4, "Маркетинг"),
                        Subject(5, "Правознавство"),
                        Subject(6, "Інформатика"),
                        Subject(7, "Цивільне право")
                    )
                )

                dao.addPrograms(
                    listOf(
                        Program(1, 1, 1, 56),
                        Program(2, 2, 1, 28),
                        Program(3, 2, 3, 24),
                        Program(4, 3, 3, 24),
                        Program(5, 2, 4, 56)
                    )
                )

                dao.addStudents(
                    listOf(
                        Student(1, "Вячеслав", "Шевчук", 2, 1, 7),
                        Student(2, "Мефодій", "Розум", 2, 1, 3),
                        Student(3, "Марина", "Кудрява", 2, 3, 1),
                        Student(4, "Кирило", "Ромашка", 1, 1, 2),
                        Student(5, "Вікторія", "Чапля", 1, 1, 5),
                        Student(6, "Олександр", "Великий", 3, 1, 1),
                        Student(7, "Роксолана", "Горова", 3, 1, 4),
                        Student(8, "Іван", "Молодець", 1, 2, 2)
                    )
                )
            }
        }
    }
}
