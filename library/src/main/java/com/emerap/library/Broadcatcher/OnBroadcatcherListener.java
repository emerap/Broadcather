package com.emerap.library.Broadcatcher;

import android.content.Context;
import android.content.Intent;

/**
 * Created by karbunkul on 12.04.17.
 */

public interface OnBroadcatcherListener {
    void onCatchBroadcast(Context context, Intent intent);
}
