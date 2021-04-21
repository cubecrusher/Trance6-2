package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
    public static Music archetype, chaozFantasy, fireAura, fireFly, lightSpeed, uD, mainMenu, blip1, blip2, gameOver;
    public static BitmapFont gui, guiSmall, gui2, gui2Small;

    public static void load(){
        archetype = Gdx.audio.newMusic(Gdx.files.internal("audio/AZ8T.mp3")); //160bpm
        archetype.setLooping(true);
        chaozFantasy = Gdx.audio.newMusic(Gdx.files.internal("audio/CF-B.mp3")); //108bpm
        chaozFantasy.setLooping(true);
        fireAura = Gdx.audio.newMusic(Gdx.files.internal("audio/FA.mp3")); //180bpm
        fireAura.setLooping(true);
        fireFly = Gdx.audio.newMusic(Gdx.files.internal("audio/FF-PRMX.mp3")); //140bpm
        fireFly.setLooping(true);
        mainMenu = Gdx.audio.newMusic(Gdx.files.internal("audio/mainmenushort.mp3"));
        mainMenu.setLooping(true);
        uD = Gdx.audio.newMusic(Gdx.files.internal("audio/UD.mp3")); //140bpm
        uD.setLooping(true);
        lightSpeed = Gdx.audio.newMusic(Gdx.files.internal("audio/ATSOL.mp3")); //160bpm
        blip1 = Gdx.audio.newMusic(Gdx.files.internal("audio/select.mp3"));
        blip2 = Gdx.audio.newMusic(Gdx.files.internal("audio/transition.mp3"));
        gameOver = Gdx.audio.newMusic(Gdx.files.internal("audio/terminated.mp3"));
        gui = new BitmapFont(Gdx.files.internal("fonts/gui.fnt"),Gdx.files.internal("fonts/gui.png"),false);
        guiSmall = new BitmapFont(Gdx.files.internal("fonts/gui_small.fnt"),Gdx.files.internal("fonts/gui_small.png"),false);
        gui2 = new BitmapFont(Gdx.files.internal("fonts/gui2.fnt"),Gdx.files.internal("fonts/gui2.png"),false);
        gui2Small = new BitmapFont(Gdx.files.internal("fonts/gui2_small.fnt"),Gdx.files.internal("fonts/gui2_small.png"),false);
        System.out.println("INFO: Assets loaded.");
    }

    public static void playSound(Music music){
        music.play();
    }

    public static void playMusic(Music music){
        music.play();
        music.isLooping();
    }

    public static void stopMusic(Music music){
        music.stop();
    }

    public static void stopAllGameMusic(){
        Assets.stopMusic(Assets.archetype);
        Assets.stopMusic(Assets.uD);
        Assets.stopMusic(Assets.fireAura);
        Assets.stopMusic(Assets.fireFly);
        Assets.stopMusic(Assets.chaozFantasy);
        Assets.stopMusic(Assets.lightSpeed);
    }

    public static void stopAllMusic(){
        Assets.stopMusic(Assets.mainMenu);
        Assets.stopMusic(Assets.archetype);
        Assets.stopMusic(Assets.uD);
        Assets.stopMusic(Assets.fireAura);
        Assets.stopMusic(Assets.fireFly);
        Assets.stopMusic(Assets.chaozFantasy);
        Assets.stopMusic(Assets.lightSpeed);
    }
}

