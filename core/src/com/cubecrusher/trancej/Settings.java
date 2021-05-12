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
    private boolean scoreSent;
    private float ehighScore, nhighScore, hhighScore, chighScore, totalTime;
    private int plays;
    private String username, difficulty;

    public Settings(){
        prefs = Gdx.app.getPreferences("TranceConfig");
        soundOn = prefs.getBoolean("sound",true);
        musicOn = prefs.getBoolean("music", true);
        fpsOn = prefs.getBoolean("fps",false);
        speedOn = prefs.getBoolean("speed",false);
        launch = prefs.getBoolean("launch",true);
        ehighScore = prefs.getFloat("ehighscore",0);
        nhighScore = prefs.getFloat("nhighscore",0);
        hhighScore = prefs.getFloat("hhighscore",0);
        chighScore = prefs.getFloat("chighscore",0);
        plays = prefs.getInteger("plays",0);
        difficulty = prefs.getString("difficulty", "Medium");
        username = prefs.getString("user", "-");
        totalTime = prefs.getFloat("total",0);
        nameSet = prefs.getBoolean("nameset",false);
        scoreSent = prefs.getBoolean("scoreSent",false);
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

    public void seteHighScore(float ehighScore){
        this.ehighScore=ehighScore;
        prefs.putFloat("ehighscore",ehighScore);
        prefs.flush();
    }
    public float geteHighScore(){
        return ehighScore;
    }

    public void setnHighScore(float nhighScore){
        this.nhighScore=nhighScore;
        prefs.putFloat("nhighscore",nhighScore);
        prefs.flush();
    }
    public float getnHighScore(){
        return nhighScore;
    }

    public void sethHighScore(float hhighScore){
        this.hhighScore=hhighScore;
        prefs.putFloat("hhighscore",hhighScore);
        prefs.flush();
    }
    public float gethHighScore(){
        return hhighScore;
    }

    public void setcHighScore(float chighScore){
        this.chighScore=chighScore;
        prefs.putFloat("chighscore",chighScore);
        prefs.flush();
    }
    public float getcHighScore(){
        return chighScore;
    }

    public void setUsername(String username){
        this.username=username;
        prefs.putString("user",username);
        prefs.flush();
    }
    public String getUsername(){
        return username;
    }

    public void setDifficulty(String difficulty){
        this.difficulty=difficulty;
        prefs.putString("difficulty",difficulty);
        prefs.flush();
    }
    public String getDifficulty(){
        return difficulty;
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

    public void setScoreSent(boolean scoreSent){
        this.scoreSent=scoreSent;
        prefs.putBoolean("scoreSent",scoreSent);
        prefs.flush();
    }
    public boolean getScoreSent(){
        return scoreSent;
    }
}
