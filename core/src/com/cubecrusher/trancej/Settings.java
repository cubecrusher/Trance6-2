package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {
    public Preferences prefs;
    private boolean soundOn;
    private boolean musicOn;
    private boolean fpsOn;
    private boolean speedOn;
    private boolean launch;
    private boolean nameSet;
    private float highScore, totalTime;
    private int plays;
    private String username;

    public Settings(){
        prefs = Gdx.app.getPreferences("TranceConfig");
        soundOn = prefs.getBoolean("sound",true);
        musicOn = prefs.getBoolean("music", true);
        fpsOn = prefs.getBoolean("fps",false);
        speedOn = prefs.getBoolean("speed",false);
        launch = prefs.getBoolean("launch",true);
        highScore = prefs.getFloat("highscore",0);
        plays = prefs.getInteger("plays",0);
        username = prefs.getString("user", "Nickname");
        totalTime = prefs.getFloat("total",0);
        nameSet = prefs.getBoolean("nameset",false);
    }

    public void setSound(boolean soundOn){
        this.soundOn=soundOn;
        prefs.putBoolean("sound",soundOn);
        prefs.flush();
    }
    public boolean isSoundOn(){
        return soundOn;
    }

    public void setMusic(boolean musicOn){
        this.musicOn=musicOn;
        prefs.putBoolean("music",soundOn);
        prefs.flush();
    }
    public boolean isMusicOn(){
        return musicOn;
    }

    public void setFps(boolean fpsOn){
        this.fpsOn=fpsOn;
        prefs.putBoolean("fps",fpsOn);
        prefs.flush();
    }
    public boolean isFpsOn(){
        return fpsOn;
    }

    public void setSpeed(boolean speedOn){
        this.speedOn=speedOn;
        prefs.putBoolean("sound",speedOn);
        prefs.flush();
    }
    public boolean isSpeedOn(){
        return speedOn;
    }

    public void setHighScore(float highScore){
        this.highScore=highScore;
        prefs.putFloat("highscore",highScore);
        prefs.flush();
    }
    public float getHighScore(){
        return highScore;
    }

    public void setUsername(String username){
        this.username=username;
        prefs.putString("user",username);
        prefs.flush();
    }
    public String getUsername(){
        return username;
    }

    public void setPlays(int plays){
        this.plays = plays;
        prefs.putInteger("plays",plays);
        prefs.flush();
    }
    public int getPlays(){
        return plays;
    }

    public void setLaunch(boolean launch){
        this.launch=launch;
        prefs.putBoolean("launch",launch);
        prefs.flush();
    }
    public boolean getLaunch(){
        return launch;
    }

    public void setTotal(float totalTime){
        this.totalTime=totalTime;
        prefs.putFloat("total",totalTime);
        prefs.flush();
    }
    public float getTotal(){
        return totalTime;
    }

    public void setNameSet(boolean nameSet){
        this.nameSet=nameSet;
        prefs.putBoolean("nameset",nameSet);
        prefs.flush();
    }
    public boolean getNameSet(){
        return nameSet;
    }
}
