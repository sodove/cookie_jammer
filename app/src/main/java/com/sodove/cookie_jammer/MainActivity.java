package com.sodove.cookie_jammer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int level = 1;
    int clicked_times = 0;

    MediaPlayer mp;
    MediaPlayer mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //????
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView cookie = findViewById(R.id.imageView);
        cookie.setClickable(true);

        mp = MediaPlayer.create(this, R.raw.cookie);
        mp2 = MediaPlayer.create(this, R.raw.brokencrystal);

        cookie.setOnClickListener(view -> {
            TextView score_text = findViewById(R.id.counter);
            int score = Integer.parseInt(score_text.getText().toString());
            score = score + level;
            clicked_times = clicked_times + 3 * level;

            if (clicked_times > 360)
                clicked_times = 0;

            cookie.setRotation(clicked_times);

            final Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.bounce);
            cookie.startAnimation(myAnim);

            score_text.setText(String.valueOf(score));

            mp.seekTo(300);
            mp.start();

            if (score > 100 * level * level){
                Button next_lvl_button = findViewById(R.id.next_level);
                next_lvl_button.setVisibility(View.VISIBLE);
            }
        });

    }
    public void onNextLevelAction(View view){
        level++;
        TextView score_text = findViewById(R.id.counter);
        TextView level_text = findViewById(R.id.level);

        mp2.start();

        level_text.setText("Level " + level);

        int score = 0;
        score_text.setText(String.valueOf(score));
        Button next_lvl_button = findViewById(R.id.next_level);
        next_lvl_button.setVisibility(View.GONE);

        ImageView cookie = findViewById(R.id.imageView);

        final Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.disappear);
        cookie.startAnimation(myAnim);
    }
}