package com.example.todo2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.todo2.R;
import com.example.todo2.model.TaskData;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder> {

    private final List<TaskData> taskDatas;
    private final LayoutInflater inflater;
    private final RecyclerDeleteButtonClickListener recyclerDeleteButtonClickListener;


    public TasksRecyclerViewAdapter(Context context, List<TaskData> taskDatas, RecyclerDeleteButtonClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.taskDatas = taskDatas;
        this.recyclerDeleteButtonClickListener = listener;
    }

    // inflates the row layout from xml when needed

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view, this.recyclerDeleteButtonClickListener);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskData taskData = taskDatas.get(position);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, taskData.getYear());
        c.set(Calendar.MONTH, taskData.getMonth());
        c.set(Calendar.DAY_OF_MONTH, taskData.getDayOfMonth());
        String dateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());

        holder.nameView.setText(taskData.getName());
        holder.categoryView.setText(taskData.getType());
        holder.dateView.setText(dateString);

    }


    @Override
    public int getItemCount() {
        return taskDatas.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView nameView;
        final TextView categoryView;
        final TextView dateView;
        final Button deleteButton;
        private final WeakReference<RecyclerDeleteButtonClickListener> listenerRef;

        ViewHolder(final View itemView, RecyclerDeleteButtonClickListener listener) {
            super(itemView);
            listenerRef = new WeakReference<>(listener);

            nameView = itemView.findViewById(R.id.nameView);
            categoryView = itemView.findViewById(R.id.categoryView);
            dateView = itemView.findViewById(R.id.dateView);


            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
// detects if delete button was clicked
            if (v.getId() == deleteButton.getId()) {
                listenerRef.get().onPositionClicked(getAdapterPosition());
            }


        }
    }


}