package com.example.todo2.modelDagger;

import com.example.todo2.Contract;
import com.example.todo2.model.MainScreenModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainScreenModelModule {
    @Provides
    Contract.presenterToMainScreenModel providePresenterToMainScreenModel(MainScreenModel mainScreenModel) {
        return mainScreenModel;
    }
}
