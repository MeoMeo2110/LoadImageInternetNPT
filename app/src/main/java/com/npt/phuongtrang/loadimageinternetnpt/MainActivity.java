package com.npt.phuongtrang.loadimageinternetnpt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    Button btnLoad;
    ImageView imgHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = (Button)findViewById(R.id.buttonLoad);
        imgHinh = (ImageView)findViewById(R.id.imageViewHinh);

        btnLoad.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new LoadImageInternet().execute("https://www.secplicity.org/wp-content/uploads/2017/05/android-1693894_1920-750x750.jpg");
            }
        });
    }

    private class LoadImageInternet extends AsyncTask<String, Void, Bitmap>
    {
        Bitmap bitmap = null;
        @Override
        protected Bitmap doInBackground(String... strings)
        {
            try
            {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
        }
    }
}