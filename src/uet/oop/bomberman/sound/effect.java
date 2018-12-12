package uet.oop.bomberman.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class  effect {

    public static Clip clip;


    public static void clips(File path) {
        try {
            AudioInputStream a;
            a = AudioSystem.getAudioInputStream(path);
            AudioFormat baseFormat = a.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, a);
            clip = AudioSystem.getClip();
            clip.open(dais);
            clip.start();
        } catch (Exception e) {
        	System.out.println(e); 
        }

    }
    
    public static void music(File path) {
        try {
            AudioInputStream a;
            a = AudioSystem.getAudioInputStream(path);
            AudioFormat baseFormat = a.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, a);
            clip = AudioSystem.getClip();
            clip.open(dais);
            clip.loop(20);
        } catch (Exception e) {
        	System.out.println(e); 
        }

    }


    public static void opera() {
        music(new File("res\\sound\\Opera.wav"));
    }
    
    public static void boom(){
        clips(new File("res\\sound\\boom.wav"));
        
    }
    public static void step(){
        clips(new File("res\\sound\\chien_mix.wav"));
    }
    public static void end(){
        clips(new File("res\\sound\\end.wav"));
        
    }
    public static void fireinhole(){
        clips(new File("res\\sound\\fireinhole.wav"));
    }
    public static void item(){
        clips(new File("res\\Sound\\item.wav"));
    }
    public static void victory(){
        clips(new File("res\\Sound\\boom.wav"));
    }
}
