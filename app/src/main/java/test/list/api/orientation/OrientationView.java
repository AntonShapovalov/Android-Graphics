package test.list.api.orientation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class OrientationView extends ViewGroup {

    private CircleView circle1;
    private CircleView circle2;

    public OrientationView(Context context) {
        super(context);
        init();
    }

    public OrientationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        circle1 = new CircleView(getContext());
        addView(circle1);
        circle2 = new CircleView(getContext());
        addView(circle2);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // do all in onSizeChanged()
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > h) {
            circle1.layout(0, 0, w / 2, h);
            circle2.layout(w / 2, 0, w, h);
        } else {
            circle1.layout(0, 0, w, h / 2);
            circle2.layout(0, h / 2, w, h);
        }
    }

}
