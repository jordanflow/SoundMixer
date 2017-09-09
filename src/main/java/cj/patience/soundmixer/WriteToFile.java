package cj.patience.soundmixer;


        import android.media.AudioRecord;
        import android.util.Log;

        import java.io.BufferedOutputStream;
        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.nio.ByteBuffer;
        import java.nio.ByteOrder;

        import static cj.patience.soundmixer.StartRecording.SAMPLE_RATE;

/**
 * Created by cj on 8/10/2017.
 */

public class WriteToFile extends MainActivity {

    public void startBufferedWrite(final File file, final AudioRecord recorder,
                                   final short[] initBuffer, final boolean isRecording){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataOutputStream output = null;
                try {
                    output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                    while (isRecording) {
                        double sum = 0;
                        int readSize = recorder.read(initBuffer, 0, initBuffer.length);
                        for (int i = 0; i < readSize; i++) {
                            output.writeShort(initBuffer[i]);
                            sum += initBuffer[i] * initBuffer[i];
                        }
                        if (readSize > 0) {
                            final double amplitude = sum / readSize;
                        }
                    }
                } catch (IOException e) {
                    Log.e("Error writing file : ", e.getMessage());
                } finally {
                    if (output != null) {
                        try {
                            output.flush();
                        } catch (IOException e) {
                            Log.e("Error writing file : ", e.getMessage());
                        } finally {
                            try {
                                output.close();
                            } catch (IOException e) {
                                Log.e("Error writing file : ", e.getMessage());
                            }
                        }
                    }
                }
            }
        }).start();
    }


    public void rawToWave(final File rawFile, final File waveFile) throws IOException {

        byte[] rawData = new byte[(int) rawFile.length()];
        DataInputStream input = null;
        try {
            input = new DataInputStream(new FileInputStream(rawFile));
            input.read(rawData);
        } finally {
            if(input != null) {
                input.close();
            }
        }
        DataOutputStream output = null;
        try {
            output = new DataOutputStream(new FileOutputStream(waveFile));
            writeString(output, "RIFF"); //chunk id
            writeInt(output, 36 + rawData.length); //chunk size
            writeString(output, "WAVE"); //format
            writeString(output, "fmt "); //subchunk 1 id
            writeInt(output, 16); //subchunk 1 size
            writeShort(output, (short) 1); // audio format (1 = PCM)
            writeShort(output, (short) 1); // number of channels
            writeInt(output, SAMPLE_RATE); // sample rate
            writeInt(output, SAMPLE_RATE * 2); // byte rate
            writeShort(output, (short) 2); // block align
            writeShort(output, (short) 16); // bits per sample
            writeString(output, "data"); // subchunk 2 id
            writeInt(output, rawData.length); // subchunk 2 size
            // Audio data (conversion big endian => little endian)
            short[] shorts = new short [rawData.length / 2];
            ByteBuffer.wrap(rawData).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
            ByteBuffer bytes = ByteBuffer.allocate(shorts.length * 2);
            for (short s : shorts) {
                bytes.putShort(s);
            }
        }finally{
            if (output != null){
                output.close();
                rawFile.delete();
            }
        }
    }

    private void writeInt(final DataOutputStream output, final int value) throws IOException{
        output.write(value >> 0);
        output.write(value >> 8);
        output.write(value >> 16);
        output.write(value >> 24);
    }

    private void writeShort(final DataOutputStream output, final short value) throws IOException{
        output.write(value >> 0);
        output.write(value >> 8);
    }

    private void writeString(final DataOutputStream output, final String value) throws IOException {
        for (int i=0; i < value.length(); i++){
            output.write(value.charAt(i));
        }
    }


}
