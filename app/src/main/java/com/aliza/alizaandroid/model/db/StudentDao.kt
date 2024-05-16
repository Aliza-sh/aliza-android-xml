package com.aliza.alizaandroid.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aliza.alizaandroid.model.data.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM student_table")
    fun getAllData(): LiveData<List<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(students: List<Student>)

    @Query("DELETE FROM student_table WHERE name = :studentName")
    fun delete(studentName: String)

}