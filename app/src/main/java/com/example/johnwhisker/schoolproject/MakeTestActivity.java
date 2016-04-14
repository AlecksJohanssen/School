package com.example.johnwhisker.schoolproject;

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
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnwhisker.schoolproject.Activities.ShowResult;
import com.firebase.client.Config;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.List;

public class MakeTestActivity extends AppCompatActivity {
    List<Question> quesList;
    int score=0;
    Firebase dtb;
    private TextView etQuestion;
    private TextView etAnswer1;
    private TextView etAnswer2;
    private TextView etAnswer3;
    private TextView etAnswer4;
    Question currentQ = new Question();
    int x = currentQ.getID();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_test);
        Firebase.setAndroidContext(this);
        dtb = new Firebase(Configuration.FIREBASE_URL);
        DBController db=new DBController(this);
        etQuestion = (TextView) findViewById(R.id.etQuestion);
        etAnswer1 = (TextView) findViewById(R.id.etAnswer1);
        etAnswer2 = (TextView) findViewById(R.id.etAnswer2);
        etAnswer3 = (TextView) findViewById(R.id.etAnswer3);
        etAnswer4 = (TextView) findViewById(R.id.etAnswer4);
        db.onCreate();
        quesList=db.getAllProducts();
        currentQ = quesList.get(x);
        setQuestionView();
        etAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(etAnswer1.getText().toString());
            }
        });
        etAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(etAnswer2.getText().toString());
            }
        });
        etAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(etAnswer3.getText().toString());
            }
        });
        etAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(etAnswer4.getText().toString());
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.make_test_menu, menu);
        return true;
    }
public void getAnswer(String answerString) {
    if (currentQ.getANSWER().equals(answerString)) {
        score++;
        Toast.makeText(MakeTestActivity.this, "YOUR SCORE IS NOW " + score, Toast.LENGTH_SHORT).show();
    }
    if (x < 3) {
        currentQ = quesList.get(x);
        setQuestionView();
    }
    else {
        Intent intent = new Intent(MakeTestActivity.this,
                ShowResult.class);
        Bundle b = new Bundle();
        b.putInt("score", score);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
}

    private void setQuestionView()
    {
        DBController db =new DBController(this);
        etQuestion.setText(currentQ.getQUESTION());
        etAnswer1.setText(currentQ.getOPTA());
        etAnswer2.setText(currentQ.getOPTB());
        etAnswer3.setText(currentQ.getOPTC());
        etAnswer4.setText(currentQ.getOPTD());
        x++;
        Log.d("currentID",String.valueOf(x));

}
public void firebaseQuestion()
{
    dtb.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                Question ques = postSnapshot.getValue(Question.class);
                if (!quesList.contains(ques)) {
                    quesList.add(ques);
                }
            }
        }
        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    });
}
}


