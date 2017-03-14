package test.list.api.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

import test.list.api.R;
import test.list.api.R.color;

@SuppressWarnings("deprecation")
public class ColorView extends View {

    private final Paint paint = new Paint();
    private int vx;
    private int vy;

    public ColorView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        vx = w;
        vy = h;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Style.FILL);
        Class<color> clas = test.list.api.R.color.class;
        int colorID;
        for (int i = 1; i < 15; i++) {
            try {
                colorID = clas.getDeclaredField("blue" + String.format("%02d", i)).getInt(clas);
            } catch (Exception e) {
                e.printStackTrace();
                colorID = R.color.blue01;
            }
            paint.setColor(getResources().getColor(colorID));
            canvas.drawRect(0, (i - 1) * (vy / 14), vx, i * (vy / 14), paint);
        }
        invalidate();
    }

}
