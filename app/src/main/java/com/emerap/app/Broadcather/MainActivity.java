package com.emerap.app.Broadcather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.emerap.library.Broadcatcher.Broadcather;
import com.emerap.library.Broadcatcher.OnBroadcatcherListener;

import static com.emerap.library.Broadcatcher.Broadcather.ACTION_NETWORK_STATE_CHANGED;
import static com.emerap.library.Broadcatcher.Broadcather.ACTION_TIME_TICK;

public class MainActivity extends AppCompatActivity implements OnBroadcatcherListener {

    private Broadcather mBroadcather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBroadcather = Broadcather.newInstance(this)
                .withAction(ACTION_NETWORK_STATE_CHANGED)
                .withAction(ACTION_TIME_TICK);

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
        Toast.makeText(this, intent.getAction(), Toast.LENGTH_LONG).show();
    }
}
