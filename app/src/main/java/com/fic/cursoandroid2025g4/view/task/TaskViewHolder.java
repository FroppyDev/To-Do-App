package com.fic.cursoandroid2025g4.view.task;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.model.task.Task;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);

    }

    public void render(Task task, TaskAdapter.OnTaskActionListener onTaskActionListener) {

        TextView tvTask = itemView.findViewById(R.id.tvTask);
        CheckBox cbMarcar = itemView.findViewById(R.id.cbMarcar);
        CardView cvTaskItem = itemView.findViewById(R.id.cvTaskItem);

        tvTask.setText(task.task_title);
        cbMarcar.setChecked(task.is_completed);

        cbMarcar.setOnClickListener(v -> {
            task.is_completed = cbMarcar.isChecked();

            if(task.is_completed) {
                tvTask.setPaintFlags(tvTask.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                tvTask.setPaintFlags(tvTask.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });

        cvTaskItem.setOnLongClickListener(v -> {

            optionsDialog(itemView.getContext(), onTaskActionListener, task);

            return true;
        });
    }

    private void optionsDialog(Context context, TaskAdapter.OnTaskActionListener onTaskActionListener, Task task) {

        Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.options_dialog, null);
        dialog.setContentView(view);

        CardView btnEdit = view.findViewById(R.id.btnEdit);
        CardView btnDelete = view.findViewById(R.id.btnDelete);

        btnEdit.setOnClickListener(v -> {
            onTaskActionListener.onEdit(task);
            dialog.dismiss();
        });

        btnDelete.setOnClickListener(v -> {
            onTaskActionListener.onDelete(task);
            dialog.dismiss();
        });

        dialog.show();

    }

}
