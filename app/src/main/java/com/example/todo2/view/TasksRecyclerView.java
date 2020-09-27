package com.example.todo2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo2.R;
import com.example.todo2.model.TaskData;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TasksRecyclerView extends RecyclerView.Adapter<TasksRecyclerView.ViewHolder>{

    private List<TaskData> mTaskDatas;
    private LayoutInflater mInflater;
    private ClickListener clickListener;

    // data is passed into the constructor
    public  TasksRecyclerView(Context context, List<TaskData> taskDatas, ClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mTaskDatas = taskDatas;
        this.clickListener=listener;
    }

    // inflates the row layout from xml when needed

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view,this.clickListener);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskData taskData = mTaskDatas.get(position);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, taskData.getYear());
        c.set(Calendar.MONTH, taskData.getMonth());
        c.set(Calendar.DAY_OF_MONTH, taskData.getDayOfMonth());
        String dateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());

        holder.nameView.setText(taskData.getName());
        holder.categoryView.setText(taskData.getType());
        holder.dateView.setText(dateString);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mTaskDatas.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameView;
        TextView categoryView;
        TextView dateView;
      Button deleteButton;
        private WeakReference<ClickListener> listenerRef;
        ViewHolder(final View itemView, ClickListener listener) {
            super(itemView);
            listenerRef = new WeakReference<>(listener);

            nameView = itemView.findViewById(R.id.nameView);
            categoryView = itemView.findViewById(R.id.categoryView);
            dateView = itemView.findViewById(R.id.dateView);


            deleteButton= itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            if (v.getId() == deleteButton.getId()) {
                listenerRef.get().onPositionClicked(getAdapterPosition());
            }


        }
    }

    // convenience method for getting data at click position
    TaskData getItem(int id) {
        return mTaskDatas.get(id);
    }


}