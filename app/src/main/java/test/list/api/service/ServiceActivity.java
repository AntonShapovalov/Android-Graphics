package test.list.api.service;

import test.list.api.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ServiceActivity extends Activity {

	private PendingIntent serviceIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		serviceIntent = PendingIntent.getService(this, 0, new Intent(this, MyService.class), 0);
		setContentView(R.layout.activity_service);

		// for testing
		TextView tv = (TextView)findViewById(R.id.text_out);
		HashMap<Integer, Integer> map = new HashMap(){{put(1,10); put(2,20);}};
//		HashMap<String, String> smap = new HashMap<>();
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            smap.put(entry.getKey().toString(), entry.getValue().toString());
//        }
//        Gson gson = new Gson();
//		String json = gson.toJson(smap);
//        //JSONObject jsonObject = new JSONObject(map);
//		tv.setText(json);
//        Type collectionType = new TypeToken<HashMap<String,String>>(){}.getType();
//        HashMap<String,String> dmap = gson.fromJson(json,collectionType);

        Gson gson = new Gson();
        String json = gson.toJson(map.keySet());
        int[] v = gson.fromJson(json,int[].class);
        tv.setText(v.toString());
	}

	public void startMyService(View view) {
		long firstTime = SystemClock.elapsedRealtime();
		// Schedule the alarm!
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 60 * 1000, serviceIntent);

		Toast.makeText(this, R.string.service_toast_scheduled, Toast.LENGTH_LONG).show();
	}

	public void stopMyService(View view) {
		// And cancel the alarm.
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(serviceIntent);

		Toast.makeText(this, R.string.service_toast_unscheduled, Toast.LENGTH_LONG).show();
	}
}
