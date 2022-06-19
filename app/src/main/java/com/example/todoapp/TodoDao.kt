package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao{
    @Insert
    suspend fun insertTask(todoModel: TodoModel):Long

    @Query("SELECT * FROM TODOMODEL WHERE finished!=-1")
    fun getTask():LiveData<List<TodoModel>>

    @Query("UPDATE TodoModel SET finished=1 where id=:uid")
    fun finishTask(uid:Long)

    @Query("delete from todomodel where id=:uid")
    fun deleteTask(uid: Long)


}