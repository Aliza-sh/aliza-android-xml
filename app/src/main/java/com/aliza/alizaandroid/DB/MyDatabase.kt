package com.aliza.alizaandroid.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [Food::class])
abstract class MyDatabase : RoomDatabase() {

    abstract val foodDao: FoodDao

    companion object {

        @Volatile
        private var DATABASE: MyDatabase? = null
        fun getDatabase(context: Context): MyDatabase {
            synchronized(this) {
                var instance = DATABASE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "myDatabase.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return instance!!
            }
        }
    }
}
