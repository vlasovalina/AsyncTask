package com.example.user.asynctask;

import android.app.Activity;
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

public class MainActivity extends Activity {
    ImageView image;
    Button runButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=(ImageView) findViewById(R.id.image1);
        runButt=(Button) findViewById(R.id.button);
    }


    String pictUrl = "http://cdn.akamai.steamstatic.com/steam/apps/17460/header.jpg?t=1447351599";


    public void runClick(View v){
        new DownloadImageTask().execute(pictUrl);
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {

            String url = "";
            if( params.length > 0 ){
                url = params[0];
            }

            InputStream input = null;
            try {
                URL urlConn = new URL(url);
                input = urlConn.openStream();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return BitmapFactory.decodeStream(input);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            image.setImageBitmap(result);
        }
    }
}

