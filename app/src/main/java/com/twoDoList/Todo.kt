package com.twoDoList

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    var title: String,
    var deadline: String,
    var isFinished: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)
