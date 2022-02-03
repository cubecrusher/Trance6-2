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
    private boolean langSet;
    private boolean epilepsy;
    private float ehighScore, nhighScore, hhighScore, chighScore, totalTime;
    private int plays;
    private int money;
    private int color;
    private int language; //  0:eng  1:rus
    private String username, difficulty;

    // Достижения
    private String acinfo;

    // Магаз
    private String storeinfo;

    public Settings() {
        prefs = Gdx.app.getPreferences("TranceConfig");
        soundOn = prefs.getBoolean("sound", true); // звук
        musicOn = prefs.getBoolean("music", true); // музыка
        fpsOn = prefs.getBoolean("fps", false); // fps - debug
        speedOn = prefs.getBoolean("speed", false); // скорость - debug
        launch = prefs.getBoolean("launch", true); // первораз для неймсет
        langSet = prefs.getBoolean("langset", false); // первораз для лангсет
        epilepsy = prefs.getBoolean("epilepsy", false); // ldm режим
        ehighScore = prefs.getFloat("ehighscore", 0); // легк
        nhighScore = prefs.getFloat("nhighscore", 0); // норм
        hhighScore = prefs.getFloat("hhighscore", 0); // хард
        chighScore = prefs.getFloat("chighscore", 0); // хардкор
        plays = prefs.getInteger("plays", 0); // попытки
        money = prefs.getInteger("money", 0); // деньги
        color = prefs.getInteger("color", 0); // 0=белый, 1=синий, 2=красный, 3=зеленый, 4=желтый
        language = prefs.getInteger("language", 1); // 1=english, 0=русский
        difficulty = prefs.getString("difficulty", "Beginner"); // сложность
        username = prefs.getString("user", " "); // никнейм
        acinfo = prefs.getString("acinfo", "000000000"); // достижения
        storeinfo = prefs.getString("storeinfo", "000000000"); // магаз
        totalTime = prefs.getFloat("total", 0); // суммвремя
        nameSet = prefs.getBoolean("nameset", false); // никнейм уже есть?
        scoreSent = prefs.getBoolean("scoreSent", false); // отправлен ли результат? (так себе)
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
        this.musicOn = musicOn;
        prefs.putBoolean("music", musicOn);
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

    public void setSpeed(boolean speedOn) {
        this.speedOn = speedOn;
        prefs.putBoolean("sound", speedOn);
        prefs.flush();
    }

    public boolean isSpeedOn() {
        return speedOn;
    }

    public boolean getEpilepsy() {
        return epilepsy;
    }

    public void setEpilepsy(boolean epilepsy) {
        this.epilepsy = epilepsy;
        prefs.putBoolean("epilepsy", epilepsy);
        prefs.flush();
    }

    public void seteHighScore(float ehighScore) {
        this.ehighScore = ehighScore;
        prefs.putFloat("ehighscore", ehighScore);
        prefs.flush();
    }

    public float geteHighScore() {
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

    public void setMoney(int money){
        this.money = money;
        prefs.putInteger("money",money);
        prefs.flush();
    }
    public int getMoney(){
        return money;
    }

    public void setLanguage(int language){
        this.language = language;
        prefs.putInteger("language",language);
        prefs.flush();
    }
    public int getLanguage(){
        return language;
    }

    public void setLaunch(boolean launch){
        this.launch=launch;
        prefs.putBoolean("launch",launch);
        prefs.flush();
    }
    public boolean getLaunch(){
        return launch;
    }

    public void setLangSet(boolean langSet){
        this.langSet=langSet;
        prefs.putBoolean("langset",langSet);
        prefs.flush();
    }
    public boolean getLangSet(){
        return langSet;
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

    public void setStoreinfo(String storeinfo){
        this.storeinfo=storeinfo;
        prefs.putString("storeinfo",storeinfo);
        prefs.flush();
    }
    public String getStoreinfo(){
        return storeinfo;
    }

    public void setAcinfo(String acinfo){
        this.acinfo=acinfo;
        prefs.putString("acinfo",acinfo);
        prefs.flush();
    }
    public String getAcinfo(){
        return acinfo;
    }

    public void setColor(int color){
        this.color = color;
        prefs.putInteger("color",color);
        prefs.flush();
    }
    public int getColor(){
        return color;
    }

}
