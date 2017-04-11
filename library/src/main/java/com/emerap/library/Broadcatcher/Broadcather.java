package com.emerap.library.Broadcatcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karbunkul on 11.04.17.
 */


public class Broadcather {

    private Context mContext;
    private BroadcastReceiver mReceiver;
    private List<String> mActions = new ArrayList<>();
    private OnBroadcatcherListener mOnBroadcatcherListener;

    public void setOnBroadcatcherListener(OnBroadcatcherListener listener) {
        mOnBroadcatcherListener = listener;
    }

    public Broadcather(@NonNull Context context) {
        mContext = context;
    }

    public Broadcather withAction(String action) {
        mActions.add(action);
        return this;
    }

    public void register() {
        setupReceiver();

        IntentFilter filter = new IntentFilter();

        for (String action : mActions) {
            filter.addAction(action);
        }

        mContext.registerReceiver(mReceiver, filter);
    }

    public void unregister() {
        mContext.unregisterReceiver(mReceiver);
    }

    private void setupReceiver() {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (mOnBroadcatcherListener != null)
                    mOnBroadcatcherListener.onCatchBroadcast(context, intent);
            }
        };
    }
}
