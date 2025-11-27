package com.fic.cursoandroid2025g4.controller.task;

import android.content.Context;
import android.util.Log;

import com.fic.cursoandroid2025g4.model.task.Task;
import com.fic.cursoandroid2025g4.model.task.TaskDao;
import com.fic.cursoandroid2025g4.model.task.TaskDatabase;

import java.util.List;

public class TaskController {

    private final TaskDao taskDao;

    public TaskController(Context context) {
        TaskDatabase database = TaskDatabase.getInstance(context);
        taskDao = database.taskDao();
    }

    //Create a book
    public boolean addTask(String title, String description, String date, boolean status){
        try{
            Task task = new Task();
            task.task_title = title;
            task.task_description = description;
            task.created_at = date;
            task.is_completed = status;
            taskDao.insertTask(task);
            Log.i("TASK_SAVE","La tarea se almacenó correctamente");
            return true;

        }catch (Exception e){
            Log.e("TASK_ERROR",e.getMessage());
            return false;
        }

    }

    //Get all task
    public List<Task> getAllTasks(){
        return taskDao.getAllTasks();
    }

    //Update task
    public void updateTask(int id, String title, String description, String date, boolean status){
        try{
            Task task = new Task();
            task.id = id;
            task.task_title = title;
            task.task_description = description;
            task.created_at = date;
            task.is_completed = status;
            taskDao.updateTask(task);
            Log.i("TASK_SAVE","La tarea se almacenó correctamente");

        }catch (Exception e){
            Log.e("TASK_ERROR",e.getMessage());
        }
    }

    //Delete task
    public void deleteTask(Task task){
        taskDao.deleteTask(task);
    }

}
