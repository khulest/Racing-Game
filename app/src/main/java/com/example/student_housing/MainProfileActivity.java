package com.example.student_housing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainProfileActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextStudentNo;
    private EditText editTextContact;
    private EditText editTextEmail;
    private EditText editTextCourse;
    private EditText editTextDisability;
    private Spinner spinnerGender, spinnerFullTime,spinnerFaculty;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextStudentNo = findViewById(R.id.editTextSTD);
        editTextContact = findViewById(R.id.editTextContact);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextCourse = findViewById(R.id.editTextCourse);
        spinnerFaculty = findViewById(R.id.spinnerFaculty);
        editTextDisability = findViewById(R.id.editTextDisability);
        spinnerGender = findViewById(R.id.spinnerGender);
        spinnerFullTime = findViewById(R.id.spinnerFullTime);

        // Set up spinner adapters
        ArrayAdapter<CharSequence> facultyAdapter = ArrayAdapter.createFromResource(this,
                R.array.faculty_array, android.R.layout.simple_spinner_item);
        facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFaculty.setAdapter(facultyAdapter);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        ArrayAdapter<CharSequence> fullTimeAdapter = ArrayAdapter.createFromResource(this,
                R.array.full_time_array, android.R.layout.simple_spinner_item);
        fullTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFullTime.setAdapter(fullTimeAdapter);

        // Set up save button click listener
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try (Database db = new Database(getApplicationContext(), "myapplication", null, 1)) {


                    // Retrieve user input from EditText fields and Spinner
                    String name = editTextName.getText().toString().trim();
                    String surname = editTextSurname.getText().toString().trim();
                    int StudentNo = 0;
                    long contact = 0;
                    try {
                        StudentNo = Integer.parseInt(editTextStudentNo.getText().toString().trim());
                        contact = Long.parseLong(editTextContact.getText().toString().trim());
                    } catch (NumberFormatException e) {
                        // Handle parsing errors
                        editTextStudentNo.setError("Please enter a valid 8-digit student number.");
                        editTextContact.setError("Please enter a valid contact number.");
                        return;
                    }
                    String email = editTextEmail.getText().toString().trim();
                    String course = editTextCourse.getText().toString().trim();
                    String faculty = spinnerFaculty.getSelectedItem().toString();
                    String disability = editTextDisability.getText().toString().trim();
                    String gender = spinnerGender.getSelectedItem().toString();
                    String fullPartTime = spinnerFullTime.getSelectedItem().toString();

                    //  valid 8-digit student number.
                    if (String.valueOf(StudentNo).length() != 8) {
                        editTextStudentNo.setError("Please enter a valid 8-digit student number.");
                        return;
                    }

                    // Validate contact (must be a 10-digit number)
                    if (String.valueOf(contact).length() != 9) {
                        editTextContact.setError("Please enter a valid 10-digit contact number.");
                        return;
                    }
                    // Validate email format
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        editTextEmail.setError("Please enter a valid email address.");
                        return;
                    }

                    // Validate email format
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        editTextEmail.setError("Please enter a valid email address.");
                        return;
                    }

                    // Ensure all required fields are filled
                    if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || course.isEmpty() || faculty.isEmpty()) {
                        // Provide feedback for required fields
                        Toast.makeText(MainProfileActivity.this, "Please fill out all required fields", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        db.insertProfile(name, surname, StudentNo, email, contact, course, faculty, disability, gender, fullPartTime);
                        startActivity(new Intent(MainProfileActivity.this, ResidencyProfileActivity.class));

                    }
                }

                // TODO: Implement saving of profile data to database or other storage

                // Display success message upon saving
                //Toast.makeText(this, "Profile saved successfully!", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainProfileActivity.this, "Profile saved successfully , Please continue to the next page", Toast.LENGTH_SHORT).show();
            }


        });


    }


}