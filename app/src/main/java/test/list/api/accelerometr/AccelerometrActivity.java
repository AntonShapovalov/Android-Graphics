package test.list.api.accelerometr;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class AccelerometrActivity extends Activity implements
		SensorEventListener {

	private TextView textView;
	private StringBuilder sb = new StringBuilder();
	private WakeLock wakeLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// screen look off - deprecated
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "pLock");

		// screen lock off - new
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// set content
		textView = new TextView(this);
		setContentView(textView);

		// register accelerometer
		SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() > 0) {
			Sensor accelerometr = sm.getSensorList(Sensor.TYPE_ACCELEROMETER)
					.get(0);
			if (!sm.registerListener(this, accelerometr,
					SensorManager.SENSOR_DELAY_GAME)) {
				textView.setText("Couldn't register sensor listener");
			}
		} else {
			textView.setText("No accelerometr installed");
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		wakeLock.acquire();
	}

	@Override
	protected void onPause() {
		wakeLock.release();
		super.onPause();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		sb.setLength(0);
		sb.append("x:").append(event.values[0]);
		sb.append("y:").append(event.values[1]);
		sb.append("z:").append(event.values[2]);
		textView.setText(sb.toString());
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

}
