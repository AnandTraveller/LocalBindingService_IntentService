package uithreadsdemo.youtube.com.uithreaddemo.intentService;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;

public class SimpleIntentService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String msg = intent.getStringExtra(PARAM_IN_MSG);
        SystemClock.sleep(3000); // 3
        // seconds
        String resultTxt = msg + " "
                + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
        Log.v("SimpleIntentService", "Handling msg: " + resultTxt);

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(IntentServiceBasicsActivity.ResponseReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
        sendBroadcast(broadcastIntent);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);

    }
}
