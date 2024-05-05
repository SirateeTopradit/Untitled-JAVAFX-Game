package main;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
/**
 * BackgroundSound class is responsible for handling the background sound and sound effects in the game.
 * This includes setting up the media player, playing, stopping, and looping the sound.
 */
public class BackgroundSound {

    private MediaPlayer mediaPlayer;
    private URL[] soundURL = new URL[8];
    private double volume = 0.25;
    private double sfxVolume = 0.1;
    /**
     * Constructor for the BackgroundSound class.
     * Initializes the sound URLs for the background sound and sound effects.
     */
    public BackgroundSound() {
        soundURL[0] = getClass().getResource("/Sound/Death-by-Glamour.mp3");
        soundURL[1] = getClass().getResource("/Sound/Once-Upon-a-Time.mp3");
        soundURL[2] = getClass().getResource("/Sound/Explosion.mp3");
        soundURL[3] = getClass().getResource("/Sound/GAME_OVER.mp3");
        soundURL[4] = getClass().getResource("/Sound/Level_Up.mp3");
        soundURL[5] = getClass().getResource("/Sound/Oof.mp3");
        soundURL[6] = getClass().getResource("/Sound/Throwing.mp3");
        soundURL[7] = getClass().getResource("/Sound/whoosh.mp3");
    }
    /**
     * Sets the media file for the media player.
     *
     * @param i  the index of the sound URL
     */
    public void setFile(int i) {
        try {
            Media sound = new Media(soundURL[i].toString());
            mediaPlayer = new MediaPlayer(sound);
            if(i<=1||i==3){
                mediaPlayer.setVolume(volume);
            }
            else mediaPlayer.setVolume(sfxVolume);
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
    public void setToVolume(double volume){
        mediaPlayer.setVolume(volume);
        setVolume(volume);
    }
    public void setToSFXVolume(double volume){
        mediaPlayer.setVolume(volume);
        setSfxVolume(volume);
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public void setSfxVolume(double sfxVolume) {
        this.sfxVolume = sfxVolume;
    }
}