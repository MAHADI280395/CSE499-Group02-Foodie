package com.cse486.ece.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.splashtext);
        typeface = Typeface.createFromAsset(getAssets(),"font/splashfont.otf");
        textView.setTypeface(typeface);

    }
}