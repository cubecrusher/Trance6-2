package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Assets {
    public static Music archetype, chaozFantasy, fireAura, fireFly, basementFloor, harmfatal, tireDmg, shinyTech, uD, mainMenu, blip1, blip2, gameOver;

    public static void load() {
        archetype = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/Az8Tn.ogg")); //160bpm
        archetype.setLooping(true);
        chaozFantasy = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/CF-Bn.ogg")); //108bpm
        chaozFantasy.setLooping(true);
        fireAura = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/FAn.ogg")); //180bpm
        fireAura.setLooping(true);
        fireFly = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/FF-PRMXn.ogg")); //140bpm
        fireFly.setLooping(true);
        mainMenu = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/mainmenushort.ogg"));
        mainMenu.setLooping(true);
        uD = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/UDn.ogg")); //140bpm
        uD.setLooping(true);
        basementFloor = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/basementfloorn.ogg")); //
        basementFloor.setLooping(true);
        harmfatal = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/harmfataln.ogg")); //
        harmfatal.setLooping(true);
        tireDmg = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/tiredmgn.ogg")); //
        tireDmg.setLooping(true);
        shinyTech = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/shinytechn.ogg")); //
        shinyTech.setLooping(true);
        blip1 = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/select.ogg"));
        // blip2 = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/transition.ogg")); неиспользованный
        gameOver = Gdx.audio.newMusic(Gdx.files.internal("audio/ogg/terminated.ogg"));
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

