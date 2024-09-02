package com.example.student_housing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 ="create table users(username text,email text,password text)";
        sqLiteDatabase.execSQL(qry1);
        String createTableQuery = "CREATE TABLE profiles (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, student_no TEXT, email TEXT, contact TEXT, course TEXT, faculty TEXT, disability TEXT, gender TEXT, fullPartTime TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);
        String createProfileTableQuery = "CREATE TABLE personal_profiles (id INTEGER PRIMARY KEY AUTOINCREMENT, religion TEXT, answer_1 TEXT, answer_2 TEXT, answer_3 TEXT, answer_4 TEXT, answer_5 TEXT, answer_6 TEXT, answer_8 TEXT)";
        sqLiteDatabase.execSQL(createProfileTableQuery);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


    }
    public void Register (String username,String email,String password){
        ContentValues cv= new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db =getWritableDatabase();
        db.insert("users", null,cv);
        db.close();
    }
    public int login(String username, String password){
        int result=0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }


    // Method to retrieve all profiles
    public Cursor getAllProfiles() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM profiles", null);
    }


    public void insertProfile(String name, String surname, int studentNo, String email, long contact, String course, String faculty, String disability, String gender, String fullPartTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("surname", surname);
        values.put("student_no", studentNo);
        values.put("email", email);
        values.put("contact", contact);
        values.put("course", course);
        values.put("faculty", faculty);
        values.put("disability", disability);
        values.put("gender", gender);
        values.put("fullPartTime",fullPartTime);

        db.insert("profiles", null, values);
        db.close();
    }

    public void insertResidencyProfile(String religion, String answer1, String answer2, String answer3, String answer4, String answer5, String answer6, String answer8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("religion", religion);
        values.put("answer_1", answer1);
        values.put("answer_2", answer2);
        values.put("answer_3", answer3);
        values.put("answer_4", answer4);
        values.put("answer_5", answer5);
        values.put("answer_6", answer6);
        values.put("answer_8", answer8);

        db.insert("personal_profiles", null, values);
        db.close();
    }

    public Cursor getAllResidencyProfiles() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM personal_profiles", null);
    }


}
