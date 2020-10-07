package com.example.todo2.presenter;

import android.Manifest;
import android.os.Environment;

import com.example.todo2.Contract;
import com.example.todo2.model.ModelApplication;
import com.example.todo2.model.RuntimePermissionHelper;
import com.example.todo2.model.TaskData;
import com.example.todo2.model.TaskDataWithId;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;


public class MainScreenPresenter implements Contract.mainScreenViewToPresenter {


    private final Contract.presenterToMainScreenView view;
    private final Contract.presenterToMainScreenModel model;
    private ArrayList<TaskDataWithId> currentTaskDataWithIds;

    @Inject
    public MainScreenPresenter(Contract.presenterToMainScreenView view, Contract.presenterToMainScreenModel model) {
        this.view = view;
        this.model = model;
        currentTaskDataWithIds = new ArrayList<>();

        askForPermissionAndInitializeDBIfAvailable();
    }

    boolean askForPermissionAndInitializeDBIfAvailable() {
        //requests storage permission
        ArrayList<String> requiredPermissions = new ArrayList<String>();
        requiredPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        requiredPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        RuntimePermissionHelper runtimePermissionHelper = new RuntimePermissionHelper(view.getActivity(), requiredPermissions);

        if (!runtimePermissionHelper.isAllPermissionAvailable()) {
            runtimePermissionHelper.requestPermissionsIfDenied();
            return false;
        }
        //triggered when user gives permission to storage
        else if (ModelApplication.getDatabaseHelper() == null) {
            File storageFile = new File(Environment.getExternalStorageDirectory() + File.separator + "mmarkieToDoApp" + File.separator);


            ModelApplication.setDatabaseHelper(view.getActivity(), "tasks.db", storageFile, false);
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void onResume() {

        if (ModelApplication.getDatabaseHelper() != null) {
            currentTaskDataWithIds = model.queryTasks();
            view.showTasks(new ArrayList<TaskData>(currentTaskDataWithIds));
        }


    }

    @Override
    public void onItemDelete(int position) {
        TaskDataWithId toDelete = currentTaskDataWithIds.get(position);
        //gets id of task because not always position== id
        model.deleteTask(toDelete.getId());
        //onResume called to show updated list
        this.onResume();

    }

    @Override
    public void onAddButtonClick() {
//checks if storage is available, otherwise tasks can not be stored
        if (askForPermissionAndInitializeDBIfAvailable()) {
            view.goToAddTaskActivity();
        }


    }
}
