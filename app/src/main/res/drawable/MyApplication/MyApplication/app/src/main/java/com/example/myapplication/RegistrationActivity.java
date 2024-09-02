package com.example.myapplication;

import android.content.Intent;
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

public class RegistrationActivity extends AppCompatActivity {
    EditText EdtUsername,EdtSurname,EdtEmail,EdtPassword,EdtConfirmPassword;
    Button btn;
    TextView TextviewReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EdtUsername =findViewById(R.id.editTextRegUsername);
        EdtSurname =findViewById(R.id.editTextSurname);
        EdtEmail =findViewById(R.id.editTextEmailAddress);
        EdtPassword =findViewById(R.id.editTextPassword);
        EdtConfirmPassword =findViewById(R.id.editTextConfirmPassword);
        btn =findViewById(R.id.btnSignUp);
        TextviewReg =findViewById(R.id.textViewExistingUser);

        TextviewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LogInActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = EdtUsername.getText().toString();
                String Surname = EdtSurname.getText().toString();
                String email = EdtEmail.getText().toString();
                String password = EdtPassword.getText().toString();
                String Confirm_password = EdtConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "myapplication",null,1);
                if(username.isEmpty() || password.isEmpty() || email.isEmpty() || Surname.isEmpty() || Confirm_password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill in all details", Toast.LENGTH_SHORT).show();
                }else {
                    if(password.compareTo(Confirm_password)==0){
                        if(isValid(password)){
                            db.Register(username, email, password);
                            Toast.makeText(getApplicationContext(),"Account created",Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"Please use you details to log in",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this, LogInActivity.class));

                        }else {
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 characters, having a letter ,digit and a special character ",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Password and Confirm Password did not Match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public static boolean isValid(String password_here){
        int f1=0,f2=0,f3=0;
        if(password_here.length()<8){
            return false;
        }else {
            for(int p=0;p<password_here.length();p++){
                if(Character.isLetter(password_here.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0;r<password_here.length();r++){
                if(Character.isDigit(password_here.charAt(r))){
                    f2=1;
                }
            }
            for(int s=0;s<password_here.length();s++){
               char c = password_here.charAt(s);
               if(c>=33&&c<=46||c==64){
                   f3 = 1;
               }
            }
            if(f1==1 && f2==1 && f3==1) {
                return true;
            }else {
                return false;
            }
        }
    }
}