package com.prashant.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {

    Button timerStartStopButton;
    Chronometer timerWatch;
    TextView timerStatus;
    boolean timerStartBtnStatus = true;
    Animation dropFromTop, rightToLeft, bottomToUp;
    ImageView ninjaWarrior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        // loading components
        timerWatch = findViewById(R.id.timer_chronometer);
        timerStartStopButton = findViewById(R.id.timer_start_stop_btn);
        timerStatus = findViewById(R.id.timer_status);
        ninjaWarrior = findViewById(R.id.ninja_loader);

        // loading animation
        dropFromTop = AnimationUtils.loadAnimation(this,R.anim.drop_from_top);
        rightToLeft = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        bottomToUp = AnimationUtils.loadAnimation(this, R.anim.bottom_to_up);

        // adding animation to ninja_warrior
        ninjaWarrior.startAnimation(dropFromTop);
        timerStatus.startAnimation(rightToLeft);
        timerStartStopButton.startAnimation(bottomToUp);

        // adding event listener
        timerStartStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerStartBtnStatus) {
                    timerWatch.setBase(SystemClock.elapsedRealtime());
                    timerWatch.start();
                    timerStartBtnStatus = false;
                    timerStartStopButton.setText("Stop");
                    timerStatus.setText("Timer Started");
                    ninjaWarrior.setImageResource(R.drawable.ninja_warrior_ready_to_fight);
                    ninjaWarrior.startAnimation(dropFromTop);
                }
                else {
                    timerWatch.stop();
                    timerStartBtnStatus = true;
                    timerStartStopButton.setText("Start");
                    timerStatus.setText("Timer Stopped");
                    ninjaWarrior.setImageResource(R.drawable.ninja_warrior_meditating);
                    ninjaWarrior.startAnimation(dropFromTop);
                }
            }
        });
    }
}
