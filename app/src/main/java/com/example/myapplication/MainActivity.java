package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView stopwatchTextView;
    private Button startButton, stopButton, restartButton;
    private boolean isRunning = false;
    private int seconds = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatchTextView = findViewById(R.id.stopwatchTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        restartButton = findViewById(R.id.restartButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStopwatch();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopStopwatch();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartStopwatch();
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                seconds++;
                updateStopwatchDisplay();
                handler.postDelayed(this, 1000);
            }
        }
    };

    private void updateStopwatchDisplay() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        stopwatchTextView.setText(time);
    }

    private void startStopwatch() {
        isRunning = true;
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        restartButton.setEnabled(false);
        handler.post(runnable);
    }

    private void stopStopwatch() {
        isRunning = false;
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        restartButton.setEnabled(true);
        handler.removeCallbacks(runnable);
    }

    private void restartStopwatch() {
        isRunning = false;
        seconds = 0;
        updateStopwatchDisplay();
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        restartButton.setEnabled(false);
        handler.removeCallbacks(runnable);
    }
}
