package com.aliza.alizaandroid.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aliza.alizaandroid.model.data.Student

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val studentDao: StudentDao

    companion object {

        @Volatile
        private var dataBase: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            synchronized(this) {
                if (dataBase == null) {
                    dataBase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "appDatabase.db"
                    )
                        .build()
                }

                return dataBase!!
            }

        }
    }
}