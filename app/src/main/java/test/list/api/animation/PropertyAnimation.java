package test.list.api.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

import test.list.api.R;

public class PropertyAnimation extends Activity {

    private ImageView farm;
    private ValueAnimator animator;
    private final Handler mainHandler = new Handler();
    private Thread animatorThread;
    private volatile boolean isRepeat;
    private SoundPool soundPool;
    private int animationSoundId = -1;
    private int clickSoundId = -1;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        try {
            AssetFileDescriptor descriptor = getAssets().openFd("InsertCoin.ogg");
            animationSoundId = soundPool.load(descriptor, 1);
            AssetFileDescriptor descriptor1 = getAssets().openFd("TaDa.ogg");
            clickSoundId = soundPool.load(descriptor1, 1);
        } catch (IOException e) {
            Log.d("LOG_TAG", "Couldn't load sound effect from asset, " + e.getMessage());
        }

        farm = (ImageView) findViewById(R.id.animation_image);
        farm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRepeat = false;
                if (animator.isRunning() || animator.isStarted()) {
                    animator.end();
                }
                farm.setRotation(0);
                soundPool.play(clickSoundId, 1, 1, 0, 0, 1);
            }
        });

        animator = ObjectAnimator.ofFloat(farm, "rotation", -6, 6);
        animator.setDuration(500);
        animator.setRepeatCount(4);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addListener(new AnimatorListener());
        animator.setStartDelay(1000);
        animator.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LOG_TAG", "On Resume");
        isRepeat = true;
        animatorThread = new Thread(new AnimatorWorker());
        animatorThread.start();
    }

    @Override
    protected void onPause() {
        isRepeat = false;
        if (animatorThread.isAlive()) {
            while (true) {
                try {
                    animatorThread.join();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onPause();
    }

    private final class AnimatorWorker implements Runnable {
        private long timePoint = 0;

        @Override
        public void run() {
            while (isRepeat) {
                // repeat each 5 seconds
                if (System.currentTimeMillis() - timePoint > 5000) {
                    timePoint = System.currentTimeMillis();
                    if (!animator.isStarted() && !animator.isRunning() && isRepeat) {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                animator.start();
                            }
                        });
                    }
                } else {
                    try {
                        Thread.sleep(50); // 0,05 sec
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private final class AnimatorListener extends AnimatorListenerAdapter {
        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            soundPool.play(animationSoundId, 1, 1, 0, 0, 1);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            Log.d("LOG_TAG", "Animation End");
            farm.setRotation(0);
        }
    }
}
