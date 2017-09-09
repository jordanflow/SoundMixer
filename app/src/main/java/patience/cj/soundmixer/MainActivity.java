package patience.cj.soundmixer;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.media.SoundPool;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final int REQUEST_RECORD_EXTERNAL_STORAGE = 300;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private boolean permissionToRecordAccepted = false;
    private boolean permissionToWriteExternalStorage = false;
    public boolean isRecording = false;
    private List<Integer> mSound = new ArrayList<>();
    private SoundPool mSoundPool;
    private static int MAX_VOLUME = 100;
    private SeekBar seekbar1;
    private SeekBar seekbar2;
    private SeekBar seekbar3;
    private SeekBar seekbar4;
    private SeekBar seekbar5;
    private SeekBar seekbar6;
    private SeekBar seekbar7;
    private SeekBar seekbar8;
    private SeekBar seekbar9;
    private SeekBar seekbar10;
    private SeekBar seekbar11;
    private SeekBar seekbar12;
    private SeekBar seekbar13;
    private SeekBar seekbar14;
    private SeekBar seekbar15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Buttons button = new Buttons();
        button.setButtonHandlers();
        initSeekBars();
        StartRecording media = new StartRecording();
        media.initRecorder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .build();
        } else {
            mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        }
        loadSoundPool();

    }

    public void onRequestPermissionsResult(int permissionNumber, String[] permissionValid, int[] results){
        switch(permissionNumber){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                if (permissionToRecordAccepted = results[0] == PackageManager.PERMISSION_GRANTED){
                    break;
                } else {
                    finish();
                }
            case REQUEST_RECORD_EXTERNAL_STORAGE:
                if (permissionToWriteExternalStorage = results[0] == PackageManager.PERMISSION_GRANTED){
                    break;
                } else{
                    finish();
                }
            default:
                finish();
        }
    }

    public void loadSoundPool() {
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

    public void initSeekBars() {
        seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekbar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekbar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekbar4 = (SeekBar) findViewById(R.id.seekBar4);
        seekbar5 = (SeekBar) findViewById(R.id.seekBar5);
        seekbar6 = (SeekBar) findViewById(R.id.seekBar6);
        seekbar7 = (SeekBar) findViewById(R.id.seekBar7);
        seekbar8 = (SeekBar) findViewById(R.id.seekBar8);
        seekbar9 = (SeekBar) findViewById(R.id.seekBar9);
        seekbar10 = (SeekBar) findViewById(R.id.seekBar10);
        seekbar11 = (SeekBar) findViewById(R.id.seekBar11);
        seekbar12 = (SeekBar) findViewById(R.id.seekBar12);
        seekbar13 = (SeekBar) findViewById(R.id.seekBar13);
        seekbar14 = (SeekBar) findViewById(R.id.seekBar14);
        seekbar15 = (SeekBar) findViewById(R.id.seekBar15);
    }

    public void seekVol(SeekBar seekBar) {
        seekBar.setMax(100);
        seekBar.setProgress(1);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        final float volume = (float) (1 - (Math.log(MAX_VOLUME - progress) / Math.log(MAX_VOLUME)));
                        System.out.println(volume);
                        System.out.println(progress);
                        MediaPlayer x = seekBarCheck(seekBar);
                        x.setVolume(volume, volume);
                    }
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
    }

    public void seekSpeed(SeekBar seekBar) {
        seekBar.setMax(80);
        seekBar.setProgress(1);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        final float volume = ((float) progress) / 30;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            MediaPlayer x = seekBarCheck(seekBar);
                            x.setPlaybackParams(new PlaybackParams().setSpeed(volume));
                            System.out.println(progress);
                            System.out.println(volume);
                        }
                    }
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
    }

    public void seekPitch(SeekBar seekBar) {
        seekBar.setMax(80);
        seekBar.setProgress(1);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        final float volume = ((float) progress) / 30;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            MediaPlayer x = seekBarCheck(seekBar);
                            x.setPlaybackParams(new PlaybackParams().setPitch(volume));
                            System.out.println(progress);
                            System.out.println(volume);
                        }
                    }
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
    }

    public MediaPlayer seekBarCheck(SeekBar seekBar) {
        if ((seekBar == seekbar1) ||
                (seekBar == seekbar6) ||
                (seekBar == seekbar11)) {
            StopRecording media = new StopRecording();
            return media.loop;
        }
        if ((seekBar == seekbar2) ||
                (seekBar == seekbar7) ||
                (seekBar == seekbar12)) {
            StopRecording media = new StopRecording();
            return media.loop1;
        }
        if ((seekBar == seekbar3) ||
                (seekBar == seekbar8) ||
                (seekBar == seekbar13)) {
            StopRecording media = new StopRecording();
            return media.loop2;
        }
        if ((seekBar == seekbar4) ||
                (seekBar == seekbar9) ||
                (seekBar == seekbar14)) {
            StopRecording media = new StopRecording();
            return media.loop3;
        }
        if ((seekBar == seekbar5) ||
                (seekBar == seekbar10) ||
                (seekBar == seekbar15)) {
            StopRecording media = new StopRecording();
            return media.loop4;
        }
        return null;
    }
}
