package com.example.todo2.presenterDagger;//package com.example.todo2.presenterDagger;

import com.example.todo2.modelDagger.MainScreenModelModule;
import com.example.todo2.view.MainScreenActivity;
import com.example.todo2.viewDagger.MainScreenActivityModule;

import dagger.Component;

@Component(modules = {MainScreenActivityModule.class, MainScreenPresenterModule.class, MainScreenModelModule.class})
public interface MainScreenPresenterComponent {
    void inject(MainScreenActivity mainScreenActivity);
}
