package com.emerap.library.Broadcatcher;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Broadcather library
 * Created by karbunkul on 11.04.17.
 */

@SuppressWarnings("unused")
public class Broadcather {

    // NETWORK ACTIONS
    final public static String ACTION_NETWORK_STATE_CHANGED = ConnectivityManager.CONNECTIVITY_ACTION;

    // BATTERY ACTIONS
    final public static String ACTION_BATTERY_CHANGED = Intent.ACTION_BATTERY_CHANGED;
    final public static String ACTION_BATTERY_LOW = Intent.ACTION_BATTERY_LOW;
    final public static String ACTION_BATTERY_OK = Intent.ACTION_BATTERY_OKAY;
    final public static String ACTION_POWER_CONNECTED = Intent.ACTION_POWER_CONNECTED;

    // SCREEN ACTIONS
    final public static String ACTION_SCREEN_ON = Intent.ACTION_SCREEN_ON;
    final public static String ACTION_SCREEN_OFF = Intent.ACTION_SCREEN_OFF;

    // TIME ACTIONS
    final public static String ACTION_TIME_CHANGED = Intent.ACTION_TIME_CHANGED;
    final public static String ACTION_TIME_TICK = Intent.ACTION_TIME_TICK;
    final public static String ACTION_TIMEZONE_CHANGED = Intent.ACTION_TIMEZONE_CHANGED;

    // BLUETOOTH ACTIONS
    final public static String ACTION_BLUETOOTH_STATE_CHANGED = BluetoothAdapter.ACTION_STATE_CHANGED;

    private Context mContext;
    private BroadcastReceiver mReceiver;
    @SuppressWarnings("WeakerAccess")
    protected List<String> mActions = new ArrayList<>();
    private OnBroadcatcherListener mOnBroadcatcherListener;

    public void setOnBroadcatcherListener(OnBroadcatcherListener listener) {
        mOnBroadcatcherListener = listener;
    }

    public Broadcather(@NonNull Context context) {
        mContext = context;
    }

    public static Broadcather newInstance(@NonNull Context context) {
        return new Broadcather(context);
    }

    /**
     * Add new action to receiver
     *
     * @param action action
     * @return this
     */
    public Broadcather withAction(String action) {
        if (!mActions.contains(action)) mActions.add(action);
        return this;
    }

    /**
     * Register receiver
     */
    public void register() {
        if (mActions != null) {
            setupReceiver();

            IntentFilter filter = new IntentFilter();

            for (String action : mActions) {
                filter.addAction(action);
            }

            try {
                mContext.registerReceiver(mReceiver, filter);
            } catch (Exception e) {
                throw new RuntimeException("Context is null", e);
            }
        }
    }

    /**
     * Unregister receiver
     */
    public void unregister() {
        if (mReceiver != null && mContext != null) mContext.unregisterReceiver(mReceiver);
    }

    /**
     * Setup receiver
     */
    private void setupReceiver() {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (mOnBroadcatcherListener != null)
                    mOnBroadcatcherListener.onCatchBroadcast(context, intent);
            }
        };
    }

    /**
     * Get actions
     *
     * @return actions list
     */
    public List<String> getActions() {
        return mActions;
    }
}
