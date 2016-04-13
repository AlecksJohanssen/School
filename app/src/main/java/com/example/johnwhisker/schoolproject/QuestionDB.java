package com.example.johnwhisker.schoolproject;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AlecksJohanssen on 4/9/2016.
 */
public class QuestionDB {
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE IF NOT EXISTS Question ( Id INTEGER PRIMARY KEY,QUESTION TEXT, A TEXT,B TEXT,C TEXT,D TEXT,ANSWER TEXT)";
        database.execSQL(query);
    }
}