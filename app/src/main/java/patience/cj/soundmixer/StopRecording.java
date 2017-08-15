package patience.cj.soundmixer;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import patience.cj.soundmixer.WriteToFile;
import patience.cj.soundmixer.StartRecording;
/**
 * Created by cj on 8/11/2017.
 */

public class StopRecording extends MainActivity{

    public String wmv;
    public List<MediaPlayer> mediaplayerArray = new ArrayList<>();
    public MediaPlayer loopRecording;
    public MediaPlayer music;
    public MediaPlayer loop;
    public MediaPlayer loop1;
    public MediaPlayer loop2;
    public MediaPlayer loop3;
    public MediaPlayer loop4;
    public String audioFilePath;

    public String saveTrack(AudioRecord recorder, File activeRecording){
        try {
            isRecording = false;
            recorder.stop();
            SimpleDateFormat days = new SimpleDateFormat("yyyy.MM.dd 'at' HH.MM.SS", Locale.US);
            String currentDateandTime = days.format(new Date());
            File waveFile = new File(Environment.getExternalStorageDirectory() + File.separator + "AudioRecord" + "." + "wav");
            WriteToFile write = new WriteToFile();
            write.rawToWave(activeRecording, waveFile);
            String newRecording = waveFile.toString();
            File from = new File(newRecording);
            File to = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
                    currentDateandTime + ".wmv");
            from.renameTo(to);
            String newRecord = to.toString();
            loopRecording = MediaPlayer.create(this, Uri.parse(newRecord));
            Log.e("path_audioFilePath", audioFilePath);
            return audioFilePath;
            }catch (Exception e) {
            Log.e("Error saving file : ", e.getMessage());
        }
        return null;
    }

    public String recordWavStop(AudioRecord recorder, File activeRecording){
        try{
            isRecording = false;
            recorder.stop();
            File waveFile = new File(Environment.getExternalStorageDirectory() + File.separator + "AudioRecord" + "." + "wav");
            WriteToFile write = new WriteToFile();
            write.rawToWave(activeRecording, waveFile);
            wmv = waveFile.toString();
            music = MediaPlayer.create(this, Uri.parse(wmv));
            mediaplayerArray.add(music);
            muteSystemSound(10);
            Log.e("path_audioFilePath", audioFilePath);
            return audioFilePath;
        } catch (Exception e) {
            Log.e("Error saving file : ", e.getMessage());
        }
        return null;
    }

    public void muteSystemSound(int level){
        AudioManager manage = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        manage.setStreamVolume(AudioManager.STREAM_ALARM, level, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        manage.setStreamVolume(AudioManager.STREAM_RING, level, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        manage.setStreamVolume(AudioManager.STREAM_SYSTEM, level, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        manage.setStreamVolume(AudioManager.STREAM_NOTIFICATION, level, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public void handOff(MediaPlayer media){
        try {
            media.start();
            mediaplayerArray.add(media);
            mediaplayerArray.remove(music);
            System.out.println(mediaplayerArray);
            media.setLooping(true);
            music.release();
        }catch (Exception e) {
            Log.e("Error playing file ; ", e.getMessage());
        }
    }

    public void createMedia() {
        if (loop == null) {
            loop = MediaPlayer.create(this, Uri.parse(wmv));
            handOff(loop);
        } else if (loop1 == null) {
            loop1 = MediaPlayer.create(this, Uri.parse(wmv));
            handOff(loop1);
        } else if (loop2 == null) {
            loop2 = MediaPlayer.create(this, Uri.parse(wmv));
            handOff(loop2);
        } else if (loop3 == null) {
            loop3 = MediaPlayer.create(this, Uri.parse(wmv));
            handOff(loop3);
        } else if (loop4 == null) {
            loop4 = MediaPlayer.create(this, Uri.parse(wmv));
            handOff(loop4);
        }
    }

    public void deletedir(File variable) {
        if (variable.isDirectory())
            for (File child : variable.listFiles())
                deletedir(child);
        if (variable != getFilesDir()) {
            variable.delete();
        }
    }

}
