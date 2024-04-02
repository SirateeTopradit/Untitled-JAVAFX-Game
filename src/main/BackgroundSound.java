package main;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class BackgroundSound {

    MediaPlayer mediaPlayer;
    URL[] soundURL = new URL[2];

    public BackgroundSound() {
        soundURL[0] = getClass().getResource("/Sound/Death-by-Glamour.mp3");
        soundURL[1] = getClass().getResource("/Sound/Once-Upon-a-Time.mp3");
    }

    public void setFile(int i) {
        try {
            Media sound = new Media(soundURL[i].toString());
            mediaPlayer = new MediaPlayer(sound);
        } catch (Exception e) {
            System.out.println("music file not found " + soundURL[i]);
            e.printStackTrace();
        }
    }

    public void play(){
        mediaPlayer.play();
    }

    public void stop(){
        mediaPlayer.stop();
    }

    public void loop(){
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
}