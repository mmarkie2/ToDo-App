package com.example.todo2.presenterDagger;

import com.example.todo2.Contract;
import com.example.todo2.presenter.AddTaskPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class AddTaskPresenterModule {

    @Provides
    Contract.addTaskViewToPresenter provideAddTaskViewToPresenter
            (AddTaskPresenter addTaskPresenter) {
        return addTaskPresenter;
    }
}
