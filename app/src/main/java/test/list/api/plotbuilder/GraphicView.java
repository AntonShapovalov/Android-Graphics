package test.list.api.plotbuilder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class GraphicView extends View {

	private final Paint paint = new Paint();
	private int width;
	private int height;
	private int radius;
	private int k = 0;
	private int b = 0;

	public GraphicView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
		radius = Math.min(w, h);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setStyle(Style.FILL);
		paint.setColor(Color.BLACK);
		final int cx = width / 2;
		final int cy = height / 2;
		// axis
		canvas.drawLine(cx, 0, cx, height, paint);
		canvas.drawLine(0, cy, width, cy, paint);
		/*
		 * move to center (cx, cy) and transpon: y = kx + b; y' - h = -k(x - w)
		 * + b; y' = -k(x - w) + b + h;
		 * 
		 * calculate for 2 point - intersect with radius area: x1 = (w - r) / 2,
		 * x2 = (w + r) / 2
		 */
		if (k != 0) {
			int x1 = (width - radius) / 2;
			int y1 = -k * (x1 - cx) + b + cy;
			int x2 = (width + radius) / 2;
			int y2 = -k * (x2 - cx) + b + cy;
			paint.setStyle(Style.STROKE);
			paint.setColor(Color.RED);
			canvas.drawLine(x1, y1, x2, y2, paint);
		}
	}

	public void setFormula(int k, int b) {
		this.k = k;
		this.b = b;
		postInvalidate();
	}
}
