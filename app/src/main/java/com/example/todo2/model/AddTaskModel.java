package com.example.todo2.model;

import android.content.ContentValues;

import com.example.todo2.Contract;

import javax.inject.Inject;


public class AddTaskModel implements Contract.addTaskPresenterToModel {


    @Inject
    public AddTaskModel() {

    }

    @Override
    public void saveTask(TaskData taskData) {

        ContentValues values = new ContentValues();
        values.put("name", taskData.getName());
        values.put("type", taskData.getType());
        values.put("year", taskData.getYear());
        values.put("month", taskData.getMonth());
        values.put("dayOfMonth", taskData.getDayOfMonth());
        ModelApplication.getDatabaseHelper().insertIntoTable("tasks", values);
    }


}
