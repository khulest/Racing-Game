package com.example.student_housing;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ApplyActivity extends AppCompatActivity {
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apply2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView textViewName = findViewById(R.id.textView27);
        TextView textViewSurname = findViewById(R.id.textView29);
        TextView textViewStudentNo = findViewById(R.id.textView31);
        TextView textViewEmail = findViewById(R.id.textView33);
        TextView textViewContact = findViewById(R.id.textView35);
        TextView textViewCourse = findViewById(R.id.textView37);
        TextView textViewFaculty = findViewById(R.id.textView39);
        TextView textViewDisability = findViewById(R.id.textView41);
        TextView textViewGender = findViewById(R.id.textView43);

        // Inside onCreate method
        TextView textView13 = findViewById(R.id.textView13);
        TextView textView16 = findViewById(R.id.textView16);
        TextView textView19 = findViewById(R.id.textView19);

// Retrieve the intent that started this activity
        Intent intent = getIntent();

// Retrieve the residencyName and residencyLocation extras from the intent
        String residencyName = intent.getStringExtra("residencyName");
        String residencyLocation = intent.getStringExtra("residencyLocation");
        String Year_of_Study = intent.getStringExtra("Year_of_Study");

// Set the retrieved values to textView13
        textView13.setText( residencyName);
        textView16.setText( residencyLocation);
        textView19.setText( Year_of_Study);



        Database db = new Database(getApplicationContext(), "myapplication", null, 1);
        // Retrieve data from the database

        Cursor cursor = db.getAllProfiles();

        // Move to the first row of the cursor
        if (cursor.moveToFirst()) {
            // Retrieve data from the cursor
            int nameIndex = cursor.getColumnIndex("name");
            int surnameIndex = cursor.getColumnIndex("surname");
            int studentNoIndex = cursor.getColumnIndex("student_no");
            int emailIndex = cursor.getColumnIndex("email");
            int contactIndex = cursor.getColumnIndex("contact");
            int courseIndex = cursor.getColumnIndex("course");
            int facultyIndex = cursor.getColumnIndex("faculty");
            int disabilityIndex = cursor.getColumnIndex("disability");
            int genderIndex = cursor.getColumnIndex("gender");
            int fullPartTimeIndex = cursor.getColumnIndex("full_part_time");


            if (nameIndex != -1 && surnameIndex != -1 && studentNoIndex != -1 && emailIndex != -1 &&
                    contactIndex != -1 && courseIndex != -1 && facultyIndex != -1 && disabilityIndex != -1 &&
                    genderIndex != -1 && fullPartTimeIndex != -1) {

                // Retrieve data from the cursor using valid column indices
                String name = cursor.getString(nameIndex);
                String surname = cursor.getString(surnameIndex);
                String studentNo = cursor.getString(studentNoIndex);
                String email = cursor.getString(emailIndex);
                String contact = cursor.getString(contactIndex);
                String course = cursor.getString(courseIndex);
                String faculty = cursor.getString(facultyIndex);
                String disability = cursor.getString(disabilityIndex);
                String gender = cursor.getString(genderIndex);
                String fullPartTime = cursor.getString(fullPartTimeIndex);


                // Set retrieved data to TextViews
                textViewName.setText(name);
                textViewSurname.setText(surname);
                textViewStudentNo.setText(studentNo);
                textViewEmail.setText(email);
                textViewContact.setText(contact);
                textViewCourse.setText(course);
                textViewFaculty.setText(faculty);
                textViewDisability.setText(disability);
                textViewGender.setText(gender);


            }

        }
        cursor.close();

        // Retrieve data from the personal_profiles table
        // Inside onCreate() method





// Initialize button3 outside the if block
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApplyActivity.this, HomeActivity.class));
            }
        });


    }
}