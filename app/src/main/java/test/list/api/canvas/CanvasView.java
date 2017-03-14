package test.list.api.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.View;
import android.view.ViewGroup;

import test.list.api.R;

@SuppressWarnings("deprecation")
public class CanvasView extends ViewGroup {

    private final View rd1View = new View(getContext());
    private final View rd2View = new View(getContext());
    private final View or1View = new View(getContext());
    private final View gr1View = new View(getContext());
    private final SimpleView view = new SimpleView(getContext());

    public CanvasView(Context context) {
        super(context);
        addView(view);
        addView(rd1View);
        addView(rd2View);
        addView(or1View);
        addView(gr1View);
        rd1View.setBackgroundColor(getResources().getColor(R.color.red01));
        rd2View.setBackgroundColor(getResources().getColor(R.color.red10));
        or1View.setBackgroundColor(getResources().getColor(R.color.orange10));
        gr1View.setBackgroundColor(getResources().getColor(R.color.green10));

    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        view.layout(0, 0, 300, 300);
        rd1View.layout(0, 0, 40, 20);
        rd2View.layout(0, 20, 40, 40);
        or1View.layout(0, 40, 40, 60);
        gr1View.layout(0, 60, 40, 80);
    }

    private class SimpleView extends View {

        private final Paint paint = new Paint();

        public SimpleView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawLine(100, 50, 200, 50, paint);
            canvas.drawLine(150, 0, 150, 100, paint);

            paint.setTextAlign(Align.CENTER);
            canvas.drawText("Text", 150, 50, paint);

            invalidate();
        }
    }

}
