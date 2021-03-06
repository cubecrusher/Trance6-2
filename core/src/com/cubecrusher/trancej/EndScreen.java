package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndScreen extends ScreenAdapter {
    public static float time;
    public static boolean newBest = false;
    private OrthographicCamera camera;
    protected GameScreen gameScreen;
    private Stage stage;
    private TextureRegion playtexturer, backtexturer;
    private TextureRegionDrawable playtexturerd, backtexturerd;
    private ImageButton playbutton, backbutton;
    private SpriteBatch batch;
    private Sprite gameovers;
    private String mockery;
    private double mockeryd = Math.random();
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private Settings settings;
    private int spritey = 0, spritey2 = 0;


    public EndScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.gameScreen = new GameScreen(camera);
        this.batch = new SpriteBatch();
        if (mockeryd<=0.1) mockery = "(!) That's a bruh moment";
        if (mockeryd>0.1 && mockeryd<=0.2) mockery = "(!) You are not speed";
        if (mockeryd>0.2 && mockeryd<=0.3) mockery = "(!) Everyone disliked that";
        if (mockeryd>0.3 && mockeryd<=0.4) mockery = "(!) Not even close";
        if (mockeryd>0.4 && mockeryd<=0.5) mockery = "(!) This is no place to die!";
        if (mockeryd>0.5 && mockeryd<=0.6) mockery = "(!) All you got?";
        if (mockeryd>0.6 && mockeryd<=0.7) mockery = "(!) Brap SFX #4";
        if (mockeryd>0.7 && mockeryd<=0.8) mockery = "(!) #yolo";
        if (mockeryd>0.8 && mockeryd<=0.9) mockery = "(!) That's kinda small. Yikes!";
        if (mockeryd>0.9 && mockeryd<=1.0) mockery = "(!) You've been gnomed!";
    }

    public void create(){
        //if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        Texture gameover = new Texture(Gdx.files.internal("textures/gameover.png"));
        gameovers = new Sprite(gameover);
        Texture playtexture = new Texture(Gdx.files.internal("textures/new/play.png"));
        Texture backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));


        playtexturer = new TextureRegion(playtexture);
        backtexturer = new TextureRegion(backtexture);

        playtexturerd = new TextureRegionDrawable(playtexturer);
        backtexturerd = new TextureRegionDrawable(backtexturer);

        playbutton = new ImageButton(playtexturerd);
        backbutton = new ImageButton(backtexturerd);

        stage = new Stage(new ScreenViewport());
        stage.addActor(playbutton);
        stage.addActor(backbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show(){
        create();
        playbutton.setPosition(0, height / 2f - 100);
        playbutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);
        if (TrJr.INSTANCE.getScrW()>=1080) {
            backbutton.setPosition(0, TrJr.INSTANCE.getScrH() / 24f);
        } else {
            backbutton.setPosition(-20, TrJr.INSTANCE.getScrH() / 12f);
            backbutton.setSize(TrJr.INSTANCE.getScrW() / 4f, TrJr.INSTANCE.getScrH() / 12f);
        }

        playbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) {
                    Assets.playSound(Assets.blip1);
                    Assets.stopMusic(Assets.mainMenu);
                }
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new GameScreen(camera));
            }
        });
        backbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                TrJr.INSTANCE.setScreen(new MainScreen(camera));
            }
        });

    }

    public void update(){
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
        if (TrJr.INSTANCE.getScrW()<1080) {
            if (spritey < TrJr.INSTANCE.getScrH() / 3.5f) {
                spritey += 25;
                spritey2 += 25;
            }
            if (spritey2 < TrJr.INSTANCE.getScrH() / 3.5f * 2) spritey2 += 25;
        } else {
            if (spritey < TrJr.INSTANCE.getScrH() / 4f) {
                spritey += 25;
                spritey2 += 25;
            }
            if (spritey2 < TrJr.INSTANCE.getScrH() / 4f * 2) spritey2 += 25;
        }
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            Gdx.input.setCatchKey(Input.Keys.BACK,true);
            TrJr.INSTANCE.setScreen(new MainScreen(camera));
        }
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        gameovers.setPosition(TrJr.INSTANCE.getScrW() / 2f - 193, TrJr.INSTANCE.getScrH() - spritey);
        gameovers.draw(batch);

        if (TrJr.INSTANCE.getScrW() < 1080) {
            if (spritey2 > TrJr.INSTANCE.getScrH() / 3.5f * 2) {
                Assets.guiSmall.draw(batch, "Time: " + time, TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 75);

                stage.act(Gdx.graphics.getDeltaTime());
                stage.draw();
            }
        } else {
            if (spritey2 > TrJr.INSTANCE.getScrH() / 4f * 2) {
                    Assets.gui.draw(batch, "Time: " + time, TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 300);
                    if (!newBest) {
                        Assets.guiSmall.draw(batch, "Best: " + settings.getHighScore(), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
                        Assets.guiSmall.draw(batch, mockery, TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 175);
                    }
                    else Assets.guiSmall.draw(batch, "(!!!) New highscore!", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
                    stage.act(Gdx.graphics.getDeltaTime());
                    stage.draw();
                }
            }
        batch.end();
    }

    @Override
    public void dispose(){

    }

    @Override
    public void hide(){

    }

}
