package com.cse486.ece.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Typeface typeface;
    private ProgressBar progressbar;
    private TextView progressCounter;
    int progress=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Activity activity = MainActivity.this;

        textView = findViewById(R.id.splashtext);
        typeface = Typeface.createFromAsset(getAssets(),"font/splashfont.otf");
        textView.setTypeface(typeface);

        progressbar = findViewById(R.id.progressbar);
        progressCounter= findViewById(R.id.progressCounter);

        progressbar.setProgress(progress);
        progressbar.setMax(100);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                progress=progress+20;
                progressbar.setProgress(progress);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressCounter.setText(String.valueOf(progress));
                    }
                });

                if(progressbar.getProgress()>=100){

                    timer.cancel();
                    Intent intent = new Intent(MainActivity.this,welcome_screen.class);
                    activity.startActivity(intent);
                    finish();
                }
            }
        },1000,300);




    }

}