package test.list.api.chart;

import android.graphics.Rect;

abstract class AbstractBar implements Bar {

    final static int MAX_COUNT = 4;
    int count = 0;
    int index;
    // legend
    int legendX;
    int legendY;
    // rectangle
    int length;
    int thick;
    int thickPadding;
    Rect rectangle;

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
