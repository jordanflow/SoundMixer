package cj.patience.soundmixer;

        import android.content.Context;
        import android.media.MediaPlayer;
        import android.os.Vibrator;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;

        import java.io.File;

/**
 * Created by cj on 8/11/2017.
 */

public class Buttons extends MainActivity {

    public void setButtonHandlers() {
        ((Button) findViewById(R.id.btnStart)).setOnClickListener(btnClick);
        ((Button) findViewById(R.id.btnStop)).setOnClickListener(btnClick);
        ((Button) findViewById(R.id.btnPlay)).setOnClickListener(btnControl);
        ((Button) findViewById(R.id.btnEnd)).setOnClickListener(btnControl);
        ((Button) findViewById(R.id.btnAdd)).setOnClickListener(btnControl);
        ((Button) findViewById(R.id.btnPlayRecording)).setOnClickListener(btnControl);
        ((Button) findViewById(R.id.btnRecordTrack)).setOnClickListener(btnSave);
        ((Button) findViewById(R.id.btnSaveTrack)).setOnClickListener(btnSave);
        ((Button) findViewById(R.id.btnSound)).setOnClickListener(btnPlay);
        ((Button) findViewById(R.id.btnLoop1)).setOnClickListener(btnPlay);
        ((Button) findViewById(R.id.btnLoop2)).setOnClickListener(btnPlay);
        ((Button) findViewById(R.id.btnLoop3)).setOnClickListener(btnPlay);
        ((Button) findViewById(R.id.btnLoop4)).setOnClickListener(btnPlay);
        ((Button) findViewById(R.id.btnLoop5)).setOnClickListener(btnPlay);
        ((Button) findViewById(R.id.btnLoop1Reset)).setOnClickListener(btnPress);
        ((Button) findViewById(R.id.btnLoop2Reset)).setOnClickListener(btnPress);
        ((Button) findViewById(R.id.btnLoop3Reset)).setOnClickListener(btnPress);
        ((Button) findViewById(R.id.btnLoop4Reset)).setOnClickListener(btnPress);
        ((Button) findViewById(R.id.btnLoop5Reset)).setOnClickListener(btnPress);
        ((Button) findViewById(R.id.btnPause)).setOnClickListener(btnPause);
        ((Button) findViewById(R.id.btnLoop1Pause)).setOnClickListener(btnPause);
        ((Button) findViewById(R.id.btnLoop2Pause)).setOnClickListener(btnPause);
        ((Button) findViewById(R.id.btnLoop3Pause)).setOnClickListener(btnPause);
        ((Button) findViewById(R.id.btnLoop4Pause)).setOnClickListener(btnPause);
        ((Button) findViewById(R.id.btnLoop5Pause)).setOnClickListener(btnPause);
    }

    public View.OnClickListener btnControl = new View.OnClickListener() {
        public void onClick(View v) {
            StopRecording media = new StopRecording();
            switch (v.getId()) {
                case R.id.btnAdd:
                    if (media.music != null){
                        media.createMedia();
                        break;
                    } else {
                        break;
                    }
                case R.id.btnPlay:
                    initS();
                    break;
                case R.id.btnEnd:
                    StartRecording ogfile = new StartRecording();
                    File file = new File(ogfile.WAV_PATH);
                    StopRecording delete = new StopRecording();
                    delete.deletedir(file);
                    System.exit(1);
                    break;
                case R.id.btnPlayRecording:
                    if (media.loopRecording != null){
                        media.loopRecording.start();
                        break;
                    } else {
                        break;
                    }
                default:
                    break;
            }
        }
    };

    public View.OnClickListener btnPlay = new View.OnClickListener() {
        public void onClick(View v) {
            StopRecording media = new StopRecording();
            SeekBars x = new SeekBars();
            try {
                switch (v.getId()) {
                    case R.id.btnLoop1:
                        if (media.loop != null){
                            media.loop.start();
                            x.setSeekbars1();
                            break;
                        } else {
                            break;
                        }
                    case R.id.btnLoop2:
                        if (media.loop1 != null){
                            media.loop1.start();
                            x.setSeekbars2();
                            break;
                        } else {
                            break;
                        }
                    case R.id.btnLoop3:
                        if (media.loop2 != null){
                            media.loop2.start();
                            x.setSeekbars3();
                            break;
                        } else {
                            break;
                        }
                    case R.id.btnLoop4:
                        if (media.loop3 != null){
                            media.loop3.start();
                            x.setSeekbars4();
                            break;
                        } else {
                            break;
                        }
                    case R.id.btnLoop5:
                        if (media.loop4 != null){
                            media.loop4.start();
                            x.setSeekbars5();
                            break;
                        } else {
                            break;
                        }
                    case R.id.btnSound: {
                        if (media.music != null) {
                            media.music.start();
                            break;
                        } else {
                            break;
                        }
                    }
                    default:
                        break;
                }
            } catch (Exception e) {
                Log.e("Error playing file : ", e.getMessage());
            }
        }
    };

    public View.OnClickListener btnPause = new View.OnClickListener() {
        public void onClick(View v) {
            StopRecording media = new StopRecording();
            switch (v.getId()) {
                case R.id.btnLoop1Pause:
                    if (media.loop != null){
                        media.loop.pause();
                        break;
                    }else {
                        break;
                    }
                case R.id.btnLoop2Pause:
                    if (media.loop1 != null){
                        media.loop1.pause();
                        break;
                    }else {
                        break;
                    }
                case R.id.btnLoop3Pause:
                    if (media.loop2 != null){
                        media.loop2.pause();
                        break;
                    }else {
                        break;
                    }
                case R.id.btnLoop4Pause:
                    if (media.loop3 != null){
                        media.loop3.pause();
                        break;
                    }else {
                        break;
                    }
                case R.id.btnLoop5Pause:
                    if (media.loop4 != null){
                        media.loop4.pause();
                        break;
                    }else {
                        break;
                    }
                case R.id.btnPause:
                    if (!(media.mediaplayerArray.isEmpty())){
                        pauseAllLoops();
                        break;
                    }else {
                        break;
                    }
                default:
                    break;
            }
        }
    };

    public static View.OnClickListener btnPress = new View.OnClickListener() {
        public void onClick(View v) {
            StopRecording media = new StopRecording();
            switch (v.getId()) {
                case R.id.btnLoop1Reset:
                    if (media.loop != null){
                        media.mediaplayerArray.remove(media.loop);
                        media.loop.release();
                        media.loop = null;
                        break;
                    } else {
                        break;
                    }
                case R.id.btnLoop2Reset:
                    if (media.loop1 != null){
                        media.mediaplayerArray.remove(media.loop1);
                        media.loop1.release();
                        media.loop1 = null;
                        break;
                    } else {
                        break;
                    }
                case R.id.btnLoop3Reset:
                    if (media.loop2 != null){
                        media.mediaplayerArray.remove(media.loop2);
                        media.loop2.release();
                        media.loop2 = null;
                        break;
                    } else {
                        break;
                    }
                case R.id.btnLoop4Reset:
                    if (media.loop3 != null){
                        media.mediaplayerArray.remove(media.loop3);
                        media.loop3.release();
                        media.loop3 = null;
                        break;
                    } else {
                        break;
                    }
                case R.id.btnLoop5Reset:
                    if (media.loop4 != null){
                        media.mediaplayerArray.remove(media.loop4);
                        media.loop4.release();
                        break;
                    } else {
                        break;
                    }
                default:
                    break;
            }
        }
    };

    public View.OnClickListener btnClick = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btnStart: {
                    StartRecording media = new StartRecording();
                    enableButtons();
                    media.RecordSingleTrack = true;
                    media.recordWavStart(media.recorder);
                    vibration(20);
                    break;
                }
                case R.id.btnStop: {
                    StartRecording start = new StartRecording();
                    StopRecording stop = new StopRecording();
                    enableButtons();
                    stop.recordWavStop(start.recorder, start.activeRecording);
                    break;
                }
            }
        }
    };

    public View.OnClickListener btnSave = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnRecordTrack: {
                    StartRecording media = new StartRecording();
                    enableButtons();
                    media.RecordSingleTrack = false;
                    media.recordWavStart(media.recorder);
                    vibration(20);
                    break;
                }
                case R.id.btnSaveTrack: {
                    StartRecording start = new StartRecording();
                    StopRecording stop = new StopRecording();
                    enableButtons();
                    stop.saveTrack(start.recorder, start.activeRecording);
                    break;
                }
            }
        }
    };

    private void enableButton(int id, boolean isEnable) {
        ((Button) findViewById(id)).setEnabled(isEnable);
    }

    private void enableButtons() {
        MainActivity recordingState = new MainActivity();
        enableButton(R.id.btnStart, recordingState.isRecording);
        enableButton(R.id.btnStop, recordingState.isRecording);
        enableButton(R.id.btnRecordTrack, recordingState.isRecording);
        enableButton(R.id.btnSaveTrack, recordingState.isRecording);
    }

    public static void pauseAllLoops() {
        StopRecording media = new StopRecording();
        if (!(media.mediaplayerArray.isEmpty())) {
            for ( MediaPlayer player : media.mediaplayerArray) {
                player.pause();
            }
        }
    }

    public void vibration(int x){
        Vibrator vibrate = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrate.vibrate(x);
    }
}
