package com.example.todo2.viewDagger;
//

import android.content.Context;

import com.example.todo2.model.TaskData;
import com.example.todo2.view.RecyclerDeleteButtonClickListener;
import com.example.todo2.view.TasksRecyclerViewAdapter;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class TasksRecyclerViewAdapterModule {
    Context context;
    List<TaskData> taskDatas;
    RecyclerDeleteButtonClickListener listener;

    public TasksRecyclerViewAdapterModule(Context context, List<TaskData> taskDatas, RecyclerDeleteButtonClickListener listener) {
        this.context = context;
        this.taskDatas = taskDatas;
        this.listener = listener;
    }

    @Provides
    TasksRecyclerViewAdapter provideTaskRecyclerViewAdapter() {
        return new TasksRecyclerViewAdapter(this.context, this.taskDatas, this.listener);
    }

}
