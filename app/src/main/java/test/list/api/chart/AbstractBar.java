package test.list.api.chart;

import android.graphics.Rect;

public abstract class AbstractBar implements Bar {

	protected final static int MAX_COUNT = 4;
	protected int count = 0;
	protected int index;
	// legend
	protected int legendX;
	protected int legendY;
	// rectangle
	protected int length;
	protected int thick;
	protected int thickPadding;
	protected Rect rectangle;

	@Override
	public int getLegendX() {
		return legendX;
	}

	@Override
	public int getLegendY() {
		return legendY;
	}

	@Override
	public void incrCount() {
		count++;
	}

	@Override
	public Rect getRectangle() {
		return rectangle;
	}

}
