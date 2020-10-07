package com.example.todo2.presenterDagger;

import com.example.todo2.Contract;
import com.example.todo2.presenter.MainScreenPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainScreenPresenterModule {
    @Provides
    Contract.mainScreenViewToPresenter provideMainScreenViewToPresenter
            (MainScreenPresenter mainScreenPresenter) {
        return mainScreenPresenter;
    }
}
