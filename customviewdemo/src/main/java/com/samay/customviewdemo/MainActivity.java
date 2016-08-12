package com.samay.customviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.samay.customviewdemo.utils.MyPageTransformer;

public class MainActivity extends Activity {
//    private ImageView imageVew;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        viewPager= (ViewPager) findViewById(R.id.view_pager);
//        viewPager.setAdapter(new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return 20;
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object object) {
//                return view == object;
//            }
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_viewpager, null);
//                ImageView imageView = (ImageView) view.findViewById(R.id.iv_movie);
//                imageView.setImageResource(R.mipmap.ic_loading);
//                container.addView(view);
//                return view;
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                View view = (View) object;
//                container.removeView(view);
//            }
//        });
//        viewPager.setOffscreenPageLimit(5);
//        viewPager.setPageTransformer(true,new MyPageTransformer());
//        imageVew= (ImageView) findViewById(R.id.image_view);
//        BitmapFactory.Options options=new BitmapFactory.Options();
//        options.inSampleSize=2;
//        Bitmap image=BitmapFactory.decodeResource(getResources(),R.drawable.picture,options);
//        Bitmap newImg= Blur.fastblur(getApplicationContext(),image,1);
//        imageVew.setImageBitmap(newImg);
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
