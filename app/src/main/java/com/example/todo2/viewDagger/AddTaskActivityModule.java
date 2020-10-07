package com.example.todo2.viewDagger;


import com.example.todo2.Contract;
import com.example.todo2.view.AddTaskActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class AddTaskActivityModule {
    private AddTaskActivity addTaskActivity;

    public AddTaskActivityModule(AddTaskActivity addTaskActivity) {
        this.addTaskActivity = addTaskActivity;
    }

    @Provides
    Contract.presenterToAddTaskView providePresenterToAddTaskView() {
        return this.addTaskActivity;
    }
}
