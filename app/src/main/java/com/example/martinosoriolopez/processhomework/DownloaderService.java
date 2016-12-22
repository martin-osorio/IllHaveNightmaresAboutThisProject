package com.example.martinosoriolopez.processhomework;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloaderService extends Service {
    private static final String TAG = "molTAG";

    public DownloaderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DownloadImage downloadImage = new DownloadImage();
        downloadImage.executeOnExecutor(DownloadImage.THREAD_POOL_EXECUTOR, null);
        return super.onStartCommand(intent, flags, startId);
    }

    private void setImage(Drawable drawable) {
        //// TODO: 12/21/2016
        //mImageView.setBackgroundDrawable(drawable);
    }

    public class DownloadImage extends AsyncTask<Void, Void, BitmapDrawable> {
        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.d(TAG, "onProgressUpdate: ");
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(BitmapDrawable bitmapDrawable) {
            Log.d(TAG, "onPostExecute: YOU MADE IT INSIDE ONPOSTEXECUTE");
            super.onPostExecute(bitmapDrawable);
        }

        @Override
        protected BitmapDrawable doInBackground(Void... voids) {
            publishProgress();
            Bitmap bitmap;
            BitmapDrawable bitmapDrawable = null;
            InputStream inputStream;
            BufferedInputStream bufferedInputStream;
            URL url;
            try {
                url = new URL("http://www.dogtrainingbasics.com/wp-content/uploads/2014/08/dog-meme-pug-life.jpg");
                inputStream = url.openStream();
                bufferedInputStream = new BufferedInputStream(inputStream);
                bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                inputStream.close();
                bufferedInputStream.close();
                bitmapDrawable = new BitmapDrawable(getApplicationContext().getResources(), bitmap);
                return bitmapDrawable;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }
}
