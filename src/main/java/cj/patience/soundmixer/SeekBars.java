package cj.patience.soundmixer;

import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.widget.SeekBar;

/**
 * Created by cj on 9/8/2017.
 */

public class SeekBars extends MainActivity {

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
    private static int MAX_VOLUME = 100;

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

    public void setSeekbars1() {
        seekVol(seekbar1);
        seekSpeed(seekbar6);
        seekPitch(seekbar11);
    }

    public void setSeekbars2() {
        seekVol(seekbar2);
        seekSpeed(seekbar7);
        seekPitch(seekbar12);
    }

    public void setSeekbars3() {
        seekVol(seekbar3);
        seekSpeed(seekbar8);
        seekPitch(seekbar13);
    }

    public void setSeekbars4() {
        seekVol(seekbar4);
        seekSpeed(seekbar9);
        seekPitch(seekbar14);
    }

    public void setSeekbars5() {
        seekVol(seekbar5);
        seekSpeed(seekbar10);
        seekPitch(seekbar15);
    }

}
