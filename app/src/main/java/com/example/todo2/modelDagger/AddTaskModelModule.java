package com.example.todo2.modelDagger;

import com.example.todo2.Contract;
import com.example.todo2.model.AddTaskModel;

import dagger.Module;
import dagger.Provides;

@Module
public class AddTaskModelModule {
    @Provides
    Contract.addTaskPresenterToModel provideAddTaskPresenterToModel(AddTaskModel addTaskModel) {
        return addTaskModel;
    }
}
