package com.example.andreavalenziano.samplejsonparser.services;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.example.andreavalenziano.samplejsonparser.R;

import java.lang.ref.WeakReference;

/**
 * Created by AndreaValenziano on 28/02/17.
 */

public class ImageDownloaderTask extends AsyncTask<String, Integer, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;

    public ImageDownloaderTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // updateProgressBar
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return ServiceUtils.downloadBitmap(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    Drawable placeholder = ContextCompat.getDrawable(imageView.getContext(), R.drawable.placeholder);
                    imageView.setImageDrawable(placeholder);
                }
            }
        }
    }
}
