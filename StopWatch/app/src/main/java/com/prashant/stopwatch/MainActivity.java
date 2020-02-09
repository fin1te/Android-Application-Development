package com.prashant.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation alphaToGo, bottomToUp, zoomToFit;
    ImageView ninjaBackground;
    Button startTrainingBtn;
    TextView bannerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // loading components
        ninjaBackground = findViewById(R.id.ninja_background);
        startTrainingBtn = findViewById(R.id.start_btn);
        bannerText = findViewById(R.id.banner_text);

        // load animations from animation file
        alphaToGo = AnimationUtils.loadAnimation(this,R.anim.atg);
        bottomToUp = AnimationUtils.loadAnimation(this, R.anim.bottom_to_up);
        zoomToFit = AnimationUtils.loadAnimation(this,R.anim.zoom_to_fit);

        // starting alpha to go animation on the ninja background
        ninjaBackground.startAnimation(alphaToGo);
        startTrainingBtn.startAnimation(bottomToUp);
        bannerText.startAnimation(zoomToFit);

        // adding event listener on start_btn
        startTrainingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(MainActivity.this, StopWatchActivity.class);
                startActivity(activity);
            }
        });
    }
}
