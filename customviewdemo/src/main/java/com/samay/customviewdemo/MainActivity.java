package com.samay.customviewdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.samay.customviewdemo.utils.Blur;

public class MainActivity extends Activity {
    private ImageView imageVew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageVew= (ImageView) findViewById(R.id.image_view);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=2;
        Bitmap image=BitmapFactory.decodeResource(getResources(),R.drawable.picture,options);
        Bitmap newImg= Blur.fastblur(getApplicationContext(),image,1);
        imageVew.setImageBitmap(newImg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
