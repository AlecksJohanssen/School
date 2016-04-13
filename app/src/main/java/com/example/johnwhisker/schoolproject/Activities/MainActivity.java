package com.example.johnwhisker.schoolproject.Activities;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnwhisker.schoolproject.AddQuestionActivity;
import com.example.johnwhisker.schoolproject.DBController;
import com.example.johnwhisker.schoolproject.MakeTestActivity;
import com.example.johnwhisker.schoolproject.Question;
import com.example.johnwhisker.schoolproject.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String MyPreferences = "MyPrefs";
    TextView lbl;
    private TextView etQuestion;
    private TextView etAnswer1;
    private TextView etAnswer2;
    private TextView etAnswer3;
    private TextView etAnswer4;
    int score = 0;
    int qid =3;
    ListView lv;
    final Context context = this;
    ListAdapter adapter;
    private Context myContext;
    Question queryQ;
    private static String DB_PATH = "/data/data/com.example.johnwhisker.schoolproject/databases/";
    private static String DB_NAME ="school.db";
    SQLiteDatabase myDataBase;
    private String fileLink;
    public static final int requestcode = 1;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cvChoice1OnSelect(View v){
        Intent i = new Intent(this, MakeTestActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
    public void cvChoice2OnSelect(View v){
        Intent i = new Intent(this, AddQuestionActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
    }

}
