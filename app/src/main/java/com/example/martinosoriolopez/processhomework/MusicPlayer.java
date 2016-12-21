package com.example.martinosoriolopez.processhomework;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

public class MusicPlayer extends Service {
    MediaPlayer mediaPlayer;
    public MusicPlayer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "SERVICE STARTED", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    public void BeginPlaying(){
        mediaPlayer = MediaPlayer.create(this, R.raw.song_one);
        mediaPlayer.start();
    }

    public void StopPlaying(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
