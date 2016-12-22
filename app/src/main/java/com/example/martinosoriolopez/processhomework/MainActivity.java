package com.example.martinosoriolopez.processhomework;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

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
        Intent intent = new Intent(this, DownloaderService.class);
        startService(intent);
    }

    public void onCountClick(View view) {
        AsyncTaskCountdown atcd = new AsyncTaskCountdown();
        atcd.executeOnExecutor(atcd.THREAD_POOL_EXECUTOR, null);
        AsyncTaskCountup atcu = new AsyncTaskCountup();
        atcu.executeOnExecutor(atcu.THREAD_POOL_EXECUTOR, null);
    }

    private class AsyncTaskCountdown extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 10 ; i > 0; i--) {
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

    private class AsyncTaskCountup extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 1 ; i < 11; i++) {
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
