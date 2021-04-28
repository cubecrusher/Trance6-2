package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
    public static Music archetype, chaozFantasy, fireAura, fireFly, basementFloor, harmfatal, tireDmg, shinyTech, uD, mainMenu, blip1, blip2, gameOver;
    public static BitmapFont gui, guiSmall, gui2, gui2Small;

    public static void load(){
        archetype = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/AZ8T.ogg")); //160bpm
        archetype.setLooping(true);
        chaozFantasy = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/CF-B.ogg")); //108bpm
        chaozFantasy.setLooping(true);
        fireAura = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/FA.ogg")); //180bpm
        fireAura.setLooping(true);
        fireFly = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/FF-PRMX.ogg")); //140bpm
        fireFly.setLooping(true);
        mainMenu = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/mainmenushort.ogg"));
        mainMenu.setLooping(true);
        uD = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/UD.ogg")); //140bpm
        uD.setLooping(true);
        basementFloor = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/basementfloor.ogg")); //
        basementFloor.setLooping(true);
        harmfatal = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/harmfatal.ogg")); //
        harmfatal.setLooping(true);
        tireDmg = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/tiredmg.ogg")); //
        tireDmg.setLooping(true);
        shinyTech = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/shinytech.ogg")); //
        shinyTech.setLooping(true);
        blip1 = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/select.ogg"));
        blip2 = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/transition.ogg"));
        gameOver = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/terminated.ogg"));
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
        Assets.stopMusic(Assets.tireDmg);
        Assets.stopMusic(Assets.harmfatal);
        Assets.stopMusic(Assets.shinyTech);
    }

    public static void stopAllMusic(){
        Assets.stopMusic(Assets.mainMenu);
        Assets.stopMusic(Assets.archetype);
        Assets.stopMusic(Assets.uD);
        Assets.stopMusic(Assets.fireAura);
        Assets.stopMusic(Assets.fireFly);
        Assets.stopMusic(Assets.chaozFantasy);
        Assets.stopMusic(Assets.tireDmg);
        Assets.stopMusic(Assets.harmfatal);
        Assets.stopMusic(Assets.shinyTech);
    }
}

