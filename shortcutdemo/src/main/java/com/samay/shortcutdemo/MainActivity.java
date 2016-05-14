package com.samay.shortcutdemo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean installed=IfAddShortCut();
        removeButton= (Button) findViewById(R.id.remove_short_cut);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IfAddShortCut()){
                    Log.d("samay","removeShortCut run");
                    removeShortCut();
                }

            }
        });
        if(!installed){
            Log.d("samay","addShortCut run");
            addShortCut();
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

    public void addShortCut(){
        Intent shortcut=new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources().getString(R.string.app_name));
        Intent.ShortcutIconResource iconResource=Intent.ShortcutIconResource.fromContext(getApplicationContext(),R.mipmap.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,iconResource);

        shortcut.putExtra("duplicate", false);
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(getApplicationContext(),MainActivity.class);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,intent);
        sendBroadcast(shortcut);
    }

    public void removeShortCut(){
        Intent shortcut=new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        shortcut.putExtra("duplicate", false);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources().getString(R.string.app_name));
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(getApplicationContext(), MainActivity.class);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        sendBroadcast(shortcut);
    }

    public boolean IfAddShortCut(){
        boolean isInstallShortCut=false;
        final ContentResolver cr=getContentResolver();
        final String AUTHORITY="com.tct.launcher.settings";
        final Uri CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/favorites?notify=true");
        Cursor cursor=cr.query(CONTENT_URI,new String[]{"title","iconResource"},"title=?",new String[]{getString(R.string.app_name)},null);
        if(cursor!=null && cursor.getCount()>0){
            isInstallShortCut=true;
            Log.d("samay", "installed");
        }
        Log.d("samay","IfAddShortCut is "+isInstallShortCut);
        return isInstallShortCut;
    }
}
