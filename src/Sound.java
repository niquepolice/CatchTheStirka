import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by 1 on 30.09.2017.
 */
public class Sound {
    public static void siren() {
        System.out.println("STIRKA");
        while(true) {
            play();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void play() {
        try {
            byte[] buf = new byte[1];
            ;
            AudioFormat af = new AudioFormat((float) 44100, 8, 1, true, false);
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
            sdl.open();
            sdl.start();
            for (int i = 0; i < 1000 * (float) 44100 / 1000; i++) {
                double angle = i / ((float) 44100 / 440) * 2.0 * Math.PI;
                buf[0] = (byte) (Math.sin(angle) * 100);
                sdl.write(buf, 0, 1);
            }
            sdl.drain();
            sdl.stop();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

