package com.tohsoft.airquality.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * Created by PhongNX on 9/18/2019.
 *
 * Service xử lý tác vụ ngầm kể cả khi app bị kill
 *
 * Note: Service này chỉ là demo, nếu app không dùng Service này cho tác vụ chạy ngầm nào thì xóa đi
 */
public class BackgroundService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
