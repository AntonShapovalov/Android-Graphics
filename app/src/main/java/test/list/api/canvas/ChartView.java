package test.list.api.canvas;

import test.list.api.R;
import test.list.api.chart.Bar;
import test.list.api.chart.HorizontalBar;
import test.list.api.chart.VerticalBar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class ChartView extends View {

	private final Bar[] bars = new Bar[24];
	private final Paint barPaint = new Paint();
	private final Paint legendPaint = new Paint();
	private final static int LEGEND_TEXT_SIZE = 12;

	private final static String logRow = "00:25,00:26," + "12:27,12:27,12:28,12:29," + "13:30,13:31,13:34";

	public ChartView(Context context) {
		super(context);
	}

	public ChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// Initialize bar
		Bar bar;
		for (int i = 0; i < bars.length; i++) {
			if (w > h) {
				bar = new VerticalBar(i, w, h, LEGEND_TEXT_SIZE);
			} else {
				bar = new HorizontalBar(i, w, h, LEGEND_TEXT_SIZE);
			}
			bars[i] = bar;
		}
		// Set run's count
		String[] times = logRow.split(",");
		for (String s : times) {
			int hour = Integer.parseInt(s.substring(0, s.indexOf(":")));
			bars[hour].incrCount();
		}
		// Set rectangle
		for (int i = 0; i < bars.length; i++) {
			bars[i].setRectangle();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		legendPaint.setTextSize(LEGEND_TEXT_SIZE);
		legendPaint.setTextAlign(Align.CENTER);
		Typeface t = legendPaint.getTypeface();
		Typeface tf = Typeface.create(t, Typeface.BOLD);
		legendPaint.setTypeface(tf);

		Bar bar;
		for (int i = 0; i < bars.length; i++) {
			bar = bars[i];
			// legend
			canvas.drawText(String.format("%02dh", i), bar.getLegendX(), bar.getLegendY(), legendPaint);
			// bar
			if (i % 2 == 0) {
				barPaint.setColor(getResources().getColor(R.color.blue01));
			} else {
				barPaint.setColor(getResources().getColor(R.color.blue10));
			}
			canvas.drawRect(bar.getRectangle(), barPaint);
		}

		invalidate();
	}
}
