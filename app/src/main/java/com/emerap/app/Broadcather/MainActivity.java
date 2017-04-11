package com.emerap.app.Broadcather;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.emerap.library.Broadcatcher.Broadcather;
import com.emerap.library.Broadcatcher.OnBroadcatcherListener;

public class MainActivity extends AppCompatActivity implements OnBroadcatcherListener {

    private Broadcather mBroadcather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBroadcather = new Broadcather(getApplicationContext())
                .withAction(ConnectivityManager.CONNECTIVITY_ACTION);

        mBroadcather.setOnBroadcatcherListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBroadcather.unregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBroadcather.register();

    }

    @Override
    public void onCatchBroadcast(Context context, Intent intent) {

    }
}
