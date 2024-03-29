package com.samay.silentinstall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends Activity {
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton= (Button) findViewById(R.id.btn_test);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SHAOHUALI","CLICK ME");
                Intent intent=new Intent(MainActivity.this,AppInstallService.class);
                intent.setAction(AppInstallService.ACTION_INSTALL);
                startService(intent);
            }
        });
        ConnectivityManager connectivityManager=(ConnectivityManager)getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        List<WifiDevice> connectedDevice=connectivityManager.getTetherConnectedSta();
        for(int i=0;i<connectedDevice.size();i++){
            WifiDevice wifiDevice=connectedDevice.get(i);
            Log.d("LSH","name is "+wifiDevice.deviceName);
            Log.d("LSH","address is "+wifiDevice.deviceAddress);
        }
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
