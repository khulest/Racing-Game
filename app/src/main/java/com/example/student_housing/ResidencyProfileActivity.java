package com.example.student_housing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResidencyProfileActivity extends AppCompatActivity {
    private Button btnBack,Submit;
    private EditText editTextReligion;
    private RadioButton radioButton1A, radioButton1B, radioButton2A, radioButton2B, radioButton3A, radioButton3B, radioButton4A, radioButton4B, radioButton5A, radioButton5B, radioButton6A, radioButton6B, radioButton8A, radioButton8B;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_residency_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextReligion = findViewById(R.id.editTextReligion);
        radioButton1A = findViewById(R.id.radioButton1A);
        radioButton1B = findViewById(R.id.radioButton1B);
        radioButton2A = findViewById(R.id.radioButton2A);
        radioButton2B = findViewById(R.id.radioButton2B);
        radioButton3A = findViewById(R.id.radioButton3A);
        radioButton3B = findViewById(R.id.radioButton3B);
        radioButton4A = findViewById(R.id.radioButton4A);
        radioButton4B = findViewById(R.id.radioButton4B);
        radioButton5A = findViewById(R.id.radioButton5A);
        radioButton5B = findViewById(R.id.radioButton5B);
        radioButton6A = findViewById(R.id.radioButton6A);
        radioButton6B = findViewById(R.id.radioButton6B);
        radioButton8A = findViewById(R.id.radioButton8A);
        radioButton8B = findViewById(R.id.radioButton8B);



        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResidencyProfileActivity.this, MainProfileActivity.class));

            }
        });


        Submit= findViewById(R.id.buttonSubmit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(), "myapplication", null, 1);
                String religion = editTextReligion.getText().toString();
                String answer1, answer2, answer3, answer4, answer5, answer6, answer8;

                if (radioButton1A.isChecked()) {
                    answer1 = radioButton1A.getText().toString();
                } else if (radioButton1B.isChecked()) {
                    answer1 = radioButton1B.getText().toString();
                } else {
                    answer1 = "";
                }

                if (radioButton2A.isChecked()) {
                    answer2 = radioButton2A.getText().toString();
                } else if (radioButton2B.isChecked()) {
                    answer2 = radioButton2B.getText().toString();
                } else {
                    answer2 = "";
                }

                if (radioButton3A.isChecked()) {
                    answer3 = radioButton3A.getText().toString();
                } else if (radioButton3B.isChecked()) {
                    answer3 = radioButton3B.getText().toString();
                } else {
                    answer3 = "";
                }

                if (radioButton4A.isChecked()) {
                    answer4 = radioButton4A.getText().toString();
                } else if (radioButton4B.isChecked()) {
                    answer4 = radioButton4B.getText().toString();
                } else {
                    answer4 = "";
                }

                if (radioButton5A.isChecked()) {
                    answer5 = radioButton5A.getText().toString();
                } else if (radioButton5B.isChecked()) {
                    answer5 = radioButton5B.getText().toString();
                } else {
                    answer5 = "";
                }

                if (radioButton6A.isChecked()) {
                    answer6 = radioButton6A.getText().toString();
                } else if (radioButton6B.isChecked()) {
                    answer6 = radioButton6B.getText().toString();
                } else {
                    answer6 = "";
                }

                if (radioButton8A.isChecked()) {
                    answer8 = radioButton8A.getText().toString();
                } else if (radioButton8B.isChecked()) {
                    answer8 = radioButton8B.getText().toString();
                } else {
                    answer8 = "";
                }

                // Check if all radio buttons are checked
                if (!answer1.isEmpty() && !answer2.isEmpty() && !answer3.isEmpty() && !answer4.isEmpty() &&
                        !answer5.isEmpty() && !answer6.isEmpty() && !answer8.isEmpty()) {
                    // Insert data into the database
                    db.insertResidencyProfile(religion, answer1, answer2, answer3, answer4, answer5, answer6, answer8);
                    Toast.makeText(ResidencyProfileActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

                    // Proceed to the next page using an intent
                    startActivity(new Intent(ResidencyProfileActivity.this, HomeActivity.class));
                } else {
                    // Display an error message if any radio button is not checked
                    Toast.makeText(ResidencyProfileActivity.this, "Please answer all questions", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }
}