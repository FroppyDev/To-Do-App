package com.fic.cursoandroid2025g4.view.task;

import android.service.autofill.OnClickAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.model.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    private final List<Task> taskList = new ArrayList<>();
    OnTaskActionListener onTaskActionListener;

    public TaskAdapter(OnTaskActionListener onTaskActionListener) {
        this.onTaskActionListener = onTaskActionListener;
    }

    public void setData(List<Task> tasks){
        taskList.clear();
        if(tasks != null){
            taskList.addAll(tasks);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item,parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.render(task, onTaskActionListener);
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public interface OnTaskActionListener {
        void onEdit(Task task);
        void onDelete(Task task);
    }
}