package com.example.student_housing;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class View_profile_Activity extends AppCompatActivity {
    private Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_profile);
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
        TextView textViewFullPartTime = findViewById(R.id.textView45);





            Database db1 = new Database(getApplicationContext(), "myapplication", null, 1);
            // Retrieve data from the database

            Cursor cursor = db1.getAllProfiles();

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
                    textViewFullPartTime.setText(fullPartTime);

                }

            }
            cursor.close();

        // Retrieve data from the personal_profiles table
        // Inside onCreate() method



        retrieveResidencyProfileData();

// Initialize button3 outside the if block
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(View_profile_Activity.this, MainProfileActivity.class));
            }
        });


    }

    private void retrieveResidencyProfileData() {
        TextView textViewReligion = findViewById(R.id.textView47);
        TextView textViewAnswer1 = findViewById(R.id.textView49);
        TextView textViewAnswer2 = findViewById(R.id.textView51);
        TextView textViewAnswer3 = findViewById(R.id.textView53);
        TextView textViewAnswer4 = findViewById(R.id.textView55);

        Database db = new Database(getApplicationContext(), "myapplication", null, 1);

        Cursor cursorPersonalProfiles = db.getAllResidencyProfiles();

        if (cursorPersonalProfiles.moveToFirst()) {
            int religionIndex = cursorPersonalProfiles.getColumnIndex("religion");
            int answer1Index = cursorPersonalProfiles.getColumnIndex("answer_1");
            int answer2Index = cursorPersonalProfiles.getColumnIndex("answer_2");
            int answer3Index = cursorPersonalProfiles.getColumnIndex("answer_3");
            int answer4Index = cursorPersonalProfiles.getColumnIndex("answer_4");

            if (religionIndex != -1 && answer1Index != -1 && answer2Index != -1 && answer3Index != -1 && answer4Index != -1) {
                String religion = cursorPersonalProfiles.getString(religionIndex);
                String answer1 = cursorPersonalProfiles.getString(answer1Index);
                String answer2 = cursorPersonalProfiles.getString(answer2Index);
                String answer3 = cursorPersonalProfiles.getString(answer3Index);
                String answer4 = cursorPersonalProfiles.getString(answer4Index);

                textViewReligion.setText(religion);
                textViewAnswer1.setText(answer1);
                textViewAnswer2.setText(answer2);
                textViewAnswer3.setText(answer3);
                textViewAnswer4.setText(answer4);
            }
        }

        cursorPersonalProfiles.close();
    }
}