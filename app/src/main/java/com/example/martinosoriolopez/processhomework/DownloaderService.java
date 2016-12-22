package com.example.martinosoriolopez.processhomework;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    public class DownloadImage extends AsyncTask<Void, Void, Bitmap> {
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
        protected void onPostExecute(Bitmap bitmap) {
            Log.d(TAG, "onPostExecute: about to create image folder");
            File targetDirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "drawable");
            Boolean ableToSave = true;
            if(!targetDirectory.exists()){
                //ableToSave = targetDirectory.mkdirs();
            }
            if(ableToSave){
                Log.d(TAG, "onPostExecute: about to create image file");
                saveImage(targetDirectory, "doge.png", bitmap, Bitmap.CompressFormat.PNG,100);
                Log.d(TAG, "onPostExecute: created image file");
            }
            Log.d(TAG, "onPostExecute: about to end the asyncTask");
            super.onPostExecute(bitmap);
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            publishProgress();
            Bitmap bitmap;
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
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Log.d(TAG, "doInBackground: IMAGE DOWNLOADED");
            return null;
        }

        public boolean saveImage(File targetDirectory, String fileName, Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
            Log.d(TAG, "saveImage: about to try and save the file");
            File file = new File(targetDirectory, fileName);
            FileOutputStream fileOutputStream = null;

            try {
                fileOutputStream = new FileOutputStream(file);
                bitmap.compress(format, quality, fileOutputStream);
                fileOutputStream.close();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "saveImage: saved file");
            return false;
        }
    }
}
