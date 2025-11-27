package com.fic.cursoandroid2025g4.model.task;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(Task task);

    @Query("SELECT * FROM Task")
    List<Task> getAllTasks();

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

}
