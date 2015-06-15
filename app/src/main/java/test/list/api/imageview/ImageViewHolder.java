package test.list.api.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;

import test.list.api.R;

public class ImageViewHolder extends ViewGroup {

    private Context context;

    public ImageViewHolder(Context context) {
        super(context);
        init(context);
    }

    public ImageViewHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        //addView(imageView1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

/*
        if (w > h) {
            imageView1.layout(0, 0, w / 4, h / 2);
            imageView2.layout(w / 2, 0, w / 2, h / 2);
        } else {
            imageView1.layout(0, 0, w / 2, h / 4);
            imageView2.layout(0, h / 2, w / 2, 3*h / 4);
        }
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;
        opts.inJustDecodeBounds = false;
        imageView1.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.image_1, opts));
        imageView2.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.image_2, opts));
*/

        // w, h - current w,h
        final int maxImageWidth = 150;
        final int imageCount = 3;
        final int imagePadding = 4;

        // calc Grid Size
        int rowCount = maxImageWidth * imageCount / w;
        if ((maxImageWidth * imageCount / w > 0) || rowCount < 1) {
            rowCount++;
        }
        int colCount = w / maxImageWidth;

        Log.d(VIEW_LOG_TAG, "w=" + w);
        Log.d(VIEW_LOG_TAG, "rowCount=" + rowCount);
        Log.d(VIEW_LOG_TAG, "colCount=" + colCount);

        // add ImageView, set Layout and Image
        int left;
        int top;
        int right;
        int bottom;
        ImageView imageView;
        Bitmap bitmap;
        int imageIndex = 0;
        int resId;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (imageIndex < imageCount) {
                    left = j * maxImageWidth + j * imagePadding;
                    top = i * maxImageWidth + i * imagePadding;
                    right = (j + 1) * maxImageWidth + j * imagePadding;
                    bottom = (i + 1) * maxImageWidth + i * imagePadding;
                    imageIndex++;

                    // add View
                    imageView = new ImageView(context);
                    addView(imageView);
                    imageView.layout(left, top, right, bottom);

                    // set Image
                    switch (imageIndex) {
                        case 1:
                            resId = R.drawable.image_1;
                            break;
                        case 2:
                            resId = R.drawable.image_2;
                            break;
                        case 3:
                            resId = R.drawable.image_3;
                            break;
                        default:
                            resId = R.drawable.image_1;
                    }
                    bitmap = ImageDecoder.decodeBitmapFromResource(context.getResources(), resId, maxImageWidth, maxImageWidth);
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // do all in onSizeChanged()
    }

}
