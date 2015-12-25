package test.list.api.canvas;

import android.app.Activity;
import android.os.Bundle;

import ru.org.adons.clog.RecyclerAdapter;

public class RenderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(new ColorView(this));
		//setContentView(new ChartView(this));
		CanvasView cw = new CanvasView(this);
		setContentView(cw);
		cw.invalidate();
	}
}
