package ro.pub.cs.systems.eim.practicaltest01var06;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var06Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String int_addr = intent.getStringExtra("internet_address");
        processingThread = new ProcessingThread(this, int_addr);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
