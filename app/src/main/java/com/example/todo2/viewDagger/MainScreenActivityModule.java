package com.example.todo2.viewDagger;

import com.example.todo2.Contract;
import com.example.todo2.view.MainScreenActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainScreenActivityModule {
    private MainScreenActivity mainScreenActivity;

    public MainScreenActivityModule(MainScreenActivity mainScreenActivity) {
        this.mainScreenActivity = mainScreenActivity;
    }

    @Provides
    Contract.presenterToMainScreenView providePresenterToMainScreenView() {
        return this.mainScreenActivity;
    }
}
