package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogInActivity extends AppCompatActivity {
    EditText EdtUsername,EdtPassword;
    Button btn;
    TextView TextviewR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EdtUsername =findViewById(R.id.editTextLoginUsername);
        EdtPassword =findViewById(R.id.editTextConfirmPassword);
        btn =findViewById(R.id.btnSignUp);
        TextviewR =findViewById(R.id.textViewExistingUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = EdtUsername.getText().toString();
                String password = EdtPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "myapplication",null,1);
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill in all details", Toast.LENGTH_SHORT).show();
                }else {
                    if(db.login(username,password)==1){
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("username",username);
                        editor.apply();
                        startActivity(new Intent(LogInActivity.this,HomeActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "Invalid user name", Toast.LENGTH_SHORT).show();


                    }

                }
            }
        });
        TextviewR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this,RegistrationActivity.class));
            }
        });

    }
}