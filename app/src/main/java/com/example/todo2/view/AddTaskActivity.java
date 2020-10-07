package com.example.todo2.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.todo2.Contract;
import com.example.todo2.R;
import com.example.todo2.presenterDagger.AddTaskPresenterComponent;
import com.example.todo2.presenterDagger.DaggerAddTaskPresenterComponent;
import com.example.todo2.viewDagger.AddTaskActivityModule;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, FailedAddTaskFragment.FailedAddTaskFragmentListener, Contract.presenterToAddTaskView {

    @Inject
    Contract.addTaskViewToPresenter presenter;
    private EditText nameEditText;
    private Spinner typeSpinner;
    private Button dateButton;
    private Button submitButton;
    private Button cancelButton;
    //stores user input
    private String taskType = null;
    private Calendar taskDate = null;

    @Override
    public void onInvalidInput(String message) {
        new FailedAddTaskFragment()
                .show(getSupportFragmentManager(), message);
    }

    @Override
    public void onCorrectInput() {
        Toast.makeText(this, "Dodano Zadanie.",
                Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        AddTaskPresenterComponent component = DaggerAddTaskPresenterComponent.builder()
                .addTaskActivityModule(new AddTaskActivityModule(this)).build();
        component.inject(this);

        //initializing views
        nameEditText = findViewById(R.id.nameEditText);


        typeSpinner = findViewById(R.id.typeSpinner);
        // Spinner click listener
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                taskType = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner Drop down elements
        List<String> types = presenter.getTypes();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        typeSpinner.setAdapter(dataAdapter);


        dateButton = findViewById(R.id.dateButton);
        dateButton.setOnClickListener(v -> new DatePickerFragment()
                .show(getSupportFragmentManager(), "date picker"));

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> presenter.onSubmitClick(nameEditText.getText().toString(), this.taskType, this.taskDate));

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> finish());


    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.taskDate = Calendar.getInstance();

        this.taskDate.set(Calendar.YEAR, year);
        this.taskDate.set(Calendar.MONTH, month);
        this.taskDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(this.taskDate.getTime());

        dateButton.setText(currentDateString);
    }

    @Override
    public void onRetry(boolean isRetry) {
        if (isRetry) {

        } else {
            finish();
        }
    }
}
