package patience.cj.soundmixer;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;

/**
 * Created by cj on 8/11/2017.
 */

public class StartRecording extends MainActivity {

    public boolean RecordSingleTrack = true;
    public static final int samplingRates[] = {16000, 11025, 11000, 8000, 6000};
    public static int SAMPLE_RATE = 16000;
    public AudioRecord recorder;
    public File activeRecording;
    private short[] initBuffer;
    public String WAV_PATH = Environment.getExternalStorageDirectory() + File.separator + "AudioRecord";



    public void recordWavStart(AudioRecord recorder){
        isRecording = true;
        muteSystemSounds(0);
        if (RecordSingleTrack){
            Buttons.pauseAllLoops();
        }
        recorder.startRecording();
        File activeRecording = new File(Environment.getExternalStorageDirectory() + File.separator + "AudioRecord" + "." + "raw");
        WriteToFile write = new WriteToFile();
        write.startBufferedWrite(activeRecording, recorder, initBuffer, true);
    }

    public void muteSystemSounds(int level){
        AudioManager manage = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        manage.setStreamVolume(AudioManager.STREAM_ALARM, level, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        manage.setStreamVolume(AudioManager.STREAM_RING, level, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        manage.setStreamVolume(AudioManager.STREAM_SYSTEM, level, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        manage.setStreamVolume(AudioManager.STREAM_NOTIFICATION, level, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    private static int getSampleRate(){
        for (int rate : samplingRates){
            int bufferSize = AudioRecord.getMinBufferSize(rate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
            if (bufferSize > 0 ){
                return rate;
            }
        }
        return SAMPLE_RATE;
    }


    public void initRecorder(){
        SAMPLE_RATE = getSampleRate();
        int bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        initBuffer = new short[bufferSize];
        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, bufferSize);
        new File(WAV_PATH).mkdir();
    }
}
