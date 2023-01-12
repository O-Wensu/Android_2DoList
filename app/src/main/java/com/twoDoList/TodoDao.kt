package com.twoDoList

import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * from todo ORDER BY id DESC")
    fun getAll(): List<Todo>

    @Query("SELECT * from todo ORDER BY id DESC LIMIT 1")
    fun getLatestTodo() : Todo

    @Query("SELECT * from todo WHERE isFinished = 'false' ORDER BY id DESC")
    fun getNotFinished() : Todo

    @Insert
    fun insert(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Update
    fun update(todo: Todo)
}