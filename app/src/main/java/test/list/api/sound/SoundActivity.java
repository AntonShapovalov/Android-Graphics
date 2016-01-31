package test.list.api.sound;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import test.list.api.R;

public class SoundActivity extends Activity implements SoundPool.OnLoadCompleteListener {

    TextView tv_load;
    TextView tv_play;
    TextView tv_complete;
    SoundPool soundPool;
    int loadID4;
    int loadID5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        tv_load = (TextView) findViewById(R.id.tv_load);
        tv_play = (TextView) findViewById(R.id.tv_play);
        tv_complete = (TextView) findViewById(R.id.tv_complete);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(this);
        loadID4 = soundPool.load(this, R.raw.sound4, 1);
        loadID5 = soundPool.load(this, R.raw.sound5, 1);
        tv_load.append("; loadID4=" + loadID4);
        tv_load.append("; loadID5=" + loadID5);

        int playID4 = soundPool.play(loadID4, 1, 1, 0, -1, 1);
        int playID5 = soundPool.play(loadID5, 1, 1, 0, -1, 1);
        tv_play.append("; playID4=" + playID4);
        tv_play.append("; playID5=" + playID5);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        tv_complete.append("; sampleID=" + sampleId);
    }

    public void play4(View view) {
        int playID4 = soundPool.play(loadID4, 1, 1, 0, -1, 1);
        tv_play.append("; playID4=" + playID4);
        tv_play.append(tv_play.getContext().getClass().getName());
    }

    public void play5(View view) {
        int playID5 = soundPool.play(loadID5, 1, 1, 0, -1, 1);
        tv_play.append("; playID5=" + playID5);
    }

}
