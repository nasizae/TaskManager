package com.example.taskmanager.data.local.db

import androidx.room.*
import com.example.taskmanager.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE id=:taskId")
    fun getById(taskId: Int): List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun upDate(task: Task)
}