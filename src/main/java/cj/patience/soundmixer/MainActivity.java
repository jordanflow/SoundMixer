package cj.patience.soundmixer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int AUDIO_PERMISSION = 100;
    private static final int EXTERNAL_STORAGE_PERMISSION = 200;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private boolean permissionToRecordAccepted = false;
    private boolean permissionToWriteExternalStorage = false;
    private SoundPool mSoundPool;
    private List<Integer> mSound = new ArrayList<>();
    public boolean isRecording = false;
    public static int SAMPLE_RATE = 44100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ActivityCompat.requestPermissions(this, permissions, AUDIO_PERMISSION);
        loadSoundPool();
        SeekBars y = new SeekBars();
        y.initSeekBars();
        Buttons x = new Buttons();
        x.setButtonHandlers();
        StartRecording m = new StartRecording();
        m.initRecorder();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case AUDIO_PERMISSION:
                if (permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    break;
                } else {
                    finish();
                }
            case EXTERNAL_STORAGE_PERMISSION:
                if (permissionToWriteExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    break;
                } else {
                    finish();
                }
            default:
                finish();
        }
    }

    public void loadSoundPool() {
        mSoundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .build();
        mSound.add(mSoundPool.load(this, R.raw.boom_kick, 1));
        mSound.add(mSoundPool.load(this, R.raw.gubbler_drum, 1));
        mSound.add(mSoundPool.load(this, R.raw.nice_one, 1));
        mSound.add(mSoundPool.load(this, R.raw.ready, 1));
        mSound.add(mSoundPool.load(this, R.raw.robot_intro, 1));
        mSound.add(mSoundPool.load(this, R.raw.thunder, 1));
        mSound.add(mSoundPool.load(this, R.raw.whoosh, 1));
    }

    public void initS() {
        System.out.println(mSound);
        Random x = new Random();
        int y = x.nextInt(7);
        mSoundPool.play(mSound.get(y), 1, 1, 1, 0, 1);
    }

}
