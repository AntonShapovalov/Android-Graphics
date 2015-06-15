package test.list.api.service;

import java.util.List;

import test.list.api.R;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	@Override
	public void onCreate() {
		Thread thr = new Thread(null, workTask, "MyService");
		Toast.makeText(this, R.string.service_toast_start, Toast.LENGTH_LONG).show();
		thr.start();
	}

	Runnable workTask = new Runnable() {
		// TODO
		public void run() {
			// runnnig process
			ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
			List<RunningAppProcessInfo> rps = am.getRunningAppProcesses();
			String pName;
			// running application
			PackageManager pm = getPackageManager();
			ApplicationInfo ai;
			String aName;
			// result string
			StringBuilder sb = new StringBuilder();
			Time time = new Time(Time.getCurrentTimezone());
			time.setToNow();
			sb.append("DAY=").append(time.monthDay).append("TIME=").append(time.hour).append(time.minute).append("\n");
			// get all running process info
			for (RunningAppProcessInfo rp : rps) {
				pName = rp.processName;
				try {
					ai = pm.getApplicationInfo(pName, 0);
					aName = (String) pm.getApplicationLabel(ai);
				} catch (NameNotFoundException e) {
					aName = "NO_NAME";
				}
				if (!(pName.startsWith("com.android") || pName.startsWith("android.") || pName.startsWith("system"))) {
					sb.append("NAME=").append(rp.processName).append("/").append(aName).append("\n");
				}
			}
			Log.i("MyService", sb.toString());
			// Done with our work... stop the service!
			MyService.this.stopSelf();
		}
	};

	@Override
	public void onDestroy() {
		Toast.makeText(this, R.string.service_toast_stop, Toast.LENGTH_LONG).show();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
