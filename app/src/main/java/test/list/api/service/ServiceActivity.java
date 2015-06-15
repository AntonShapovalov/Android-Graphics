package test.list.api.service;

import test.list.api.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public class ServiceActivity extends Activity {

	private PendingIntent serviceIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		serviceIntent = PendingIntent.getService(this, 0, new Intent(this, MyService.class), 0);
		setContentView(R.layout.activity_service);
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
