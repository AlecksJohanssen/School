package com.example.johnwhisker.schoolproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.johnwhisker.schoolproject.R;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowResult extends AppCompatActivity {
    @Bind(R.id.ivInsideCircle)
    ImageView ivInsideCircle;
    @Bind(R.id.ivOutsideCircle)
    ImageView ivOutsideCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
       // overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.inside).into(ivInsideCircle);
        Glide.with(this).load(R.drawable.outside).into(ivOutsideCircle);
        Random rn = new Random();
        setCircleResult(rn.nextInt(100-0)+1);


    }
    public void setCircleResult(int percent){
        float Drawvalue;
        Drawvalue = -(100-percent)*0.01f;
        ivInsideCircle.animate().scaleXBy(Drawvalue).scaleYBy(Drawvalue).setDuration(2000);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


}
