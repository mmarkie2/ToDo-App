package com.example.todo2.model;

import com.example.todo2.Contract;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainScreenModel implements Contract.presenterToMainScreenModel {
    @Inject
    public MainScreenModel() {
    }

    //queering ids for deleting purpose
    @Override
    public ArrayList<TaskDataWithId> queryTasks() {
        ArrayList<ArrayList<String>> query = ModelApplication.getDatabaseHelper().selectFromTable
                ("tasks", "id, name, type, year, month, dayOfMonth");

        ArrayList<TaskDataWithId> taskDataWithIds = new ArrayList<>();
        for (ArrayList<String> row : query) {
            taskDataWithIds.add(new TaskDataWithId(Integer.parseInt(row.get(0)), row.get(1), row.get(2),
                    Integer.parseInt(row.get(3)), Integer.parseInt(row.get(4)), Integer.parseInt(row.get(5))));
        }
        return taskDataWithIds;
    }

    @Override
    public void deleteTask(int taskId) {
        ModelApplication.getDatabaseHelper().deleteFromTable("tasks", taskId);
    }
}
