package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //MediaPlayer
    MediaPlayer mp;
    SeekBar sb;
    CountDownTimer cdt;
    //Boolean to check if start is on or not.
    boolean sos = false;
    //false = we have not started
    TextView tv ;
    public void startorstop(View view){
        ImageView iv = (ImageView) findViewById(R.id.imgv);
        Button b = (Button) findViewById(R.id.but);
        //Setting editable of Seekbar to false;
        //multiply sb progress by thousand bcoz it is in milliseconds

        if(!sos) {
            sb.setEnabled(false);
            b.setText("STOP");
            sos = true;
            //start countdown
            cdt = new CountDownTimer(sb.getProgress()*1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int timeInSeconds = (int) millisUntilFinished/1000;
                    int minutes = timeInSeconds/60;
                    int seconds = timeInSeconds%60;
                    //to change the seekbar progress
                    sb.setProgress(timeInSeconds);
                    String str = minutes + " : "+seconds;
                    tv.setText(str);
                }

                @Override
                public void onFinish() {
                    //add music here to finish the countdown
                    mp.start();
                }
            };
            cdt.start();

        }else{
            sb.setEnabled(true);
            //set
            b.setText("START");
            sos = false;
            //stop countdown
            cdt.cancel();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb = (SeekBar) findViewById(R.id.SB);
        tv =  (TextView)findViewById(R.id.tvi);
        mp = MediaPlayer.create(this,R.raw.airhorn);
        sb.setMin(0);
        sb.setMax(300);
        sb.setProgress(150);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = progress/60;
                int seconds = progress%60;
                String str = minutes + " : "+seconds;
                tv.setText(str);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}