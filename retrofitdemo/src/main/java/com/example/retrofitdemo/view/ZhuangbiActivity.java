package com.example.retrofitdemo.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.adapter.ZhuangbiAdapter;
import com.example.retrofitdemo.bean.ZhuangbiImage;
import com.example.retrofitdemo.utils.Network;
import com.example.retrofitdemo.utils.ZhuangbiApi;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shaohua.li on 5/16/16.
 */
public class ZhuangbiActivity extends Activity implements CompoundButton.OnCheckedChangeListener {
    AppCompatRadioButton searchBtn_one;
    AppCompatRadioButton searchBtn_two;
    AppCompatRadioButton searchBtn_three;
    AppCompatRadioButton searchBtn_four;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ZhuangbiAdapter adapter = new ZhuangbiAdapter();
    Subscription subscription;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuangbi);
        init();
    }

    private void init(){
        searchBtn_one= (AppCompatRadioButton) findViewById(R.id.searchRb1);
        searchBtn_two= (AppCompatRadioButton) findViewById(R.id.searchRb2);
        searchBtn_three= (AppCompatRadioButton) findViewById(R.id.searchRb3);
        searchBtn_four= (AppCompatRadioButton) findViewById(R.id.searchRb4);
        searchBtn_one.setOnCheckedChangeListener(this);
        searchBtn_two.setOnCheckedChangeListener(this);
        searchBtn_three.setOnCheckedChangeListener(this);
        searchBtn_four.setOnCheckedChangeListener(this);

        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView= (RecyclerView) findViewById(R.id.gridRv);

        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);

        Log.d("ZhuangbiActivity@@","init view");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("ZhuangbiActivity@@","onCheckedChanged");
       if(isChecked){
           Log.d("ZhuangbiActivity@@","checked");
           unsubscribe();
           adapter.setImages(null);
           swipeRefreshLayout.setRefreshing(false);
           Log.d("ZhuangbiActivity@@","info is "+buttonView.getText().toString());
           search(buttonView.getText().toString());

           progressDialog=ProgressDialog.show(this,null,"loading");
       }
    }
    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private void search(String key){
        subscription= Network.getZhuangbiApi()
                    .search(key)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
    }

    Observer<List<ZhuangbiImage>> observer = new Observer<List<ZhuangbiImage>>() {
        @Override
        public void onCompleted() {
            Log.d("ZhuangbiActivity@@","observer completed");
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }

        @Override
        public void onError(Throwable e) {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getBaseContext(), "load fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<ZhuangbiImage> images) {
            Log.d("ZhuangbiActivity@@","observer onNext");
            swipeRefreshLayout.setRefreshing(false);
            adapter.setImages(images);
        }
    };

}
