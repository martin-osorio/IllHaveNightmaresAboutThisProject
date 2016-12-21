package com.example.martinosoriolopez.processhomework;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "molTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayClick(View view) {
        Intent intent = new Intent(this, MusicPlayer.class);
        intent.putExtra("command", "play");
        startService(intent);
    }

    public void onStopClick(View view) {
        Intent intent = new Intent(this, MusicPlayer.class);
        intent.putExtra("command", "stop");
        startService(intent);
    }

    public void onDownloadClick(View view) {

    }

    public void onRaceClick(View view) {
        AsyncTaskCountdown atcd = new AsyncTaskCountdown();
        AsyncTaskCountup atcu = new AsyncTaskCountup();
        atcd.execute();
        atcu.execute();
    }

    private class AsyncTaskCountdown extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0 ; i < 10; i++) {
                Log.d(TAG, "CountDOWN:      " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    private class AsyncTaskCountup extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 20 ; i > 10; i--) {
                Log.d(TAG, "CountUP:        " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
