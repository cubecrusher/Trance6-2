package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainScreen extends ScreenAdapter {
    public static float best;
    private OrthographicCamera camera;
    private Stage stage;
    private TextureRegion playtexturer, opttexturer, scoretexturer, exittexturer;
    private TextureRegionDrawable playtexturerd, opttexturerd, scoretexturerd, exittexturerd;
    private ImageButton playbutton, optbutton, scorebutton, exitbutton;
    private SpriteBatch batch;
    private Sprite gamelogos;
    private boolean launch;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private Settings settings;
    Vector3 touchpt = new Vector3();

    //private Player player;
    //private Obstacle obstacle;


    public MainScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.batch = new SpriteBatch();
        this.launch = settings.getLaunch();
    }

    public void create(){
            Texture gamelogo = new Texture(Gdx.files.internal("textures/gamelogo.png"));
            gamelogos = new Sprite(gamelogo);
            Texture playtexture = new Texture(Gdx.files.internal("textures/new/play.png"));
            Texture opttexture = new Texture(Gdx.files.internal("textures/new/options.png"));
            Texture scoretexture = new Texture(Gdx.files.internal("textures/new/score.png"));
            Texture exittexture = new Texture(Gdx.files.internal("textures/new/exit.png"));


            playtexturer = new TextureRegion(playtexture);
            opttexturer = new TextureRegion(opttexture);
            scoretexturer = new TextureRegion(scoretexture);
            exittexturer = new TextureRegion(exittexture);

            playtexturerd = new TextureRegionDrawable(playtexturer);
            opttexturerd = new TextureRegionDrawable(opttexturer);
            scoretexturerd = new TextureRegionDrawable(scoretexturer);
            exittexturerd = new TextureRegionDrawable(exittexturer);

            playbutton = new ImageButton(playtexturerd);
            optbutton = new ImageButton(opttexturerd);
            scorebutton = new ImageButton(scoretexturerd);
            exitbutton = new ImageButton(exittexturerd);

            stage = new Stage(new ScreenViewport());

            if (TrJr.INSTANCE.getScrW()>=1080) {
                opttexture = new Texture(Gdx.files.internal("textures/new/options.png"));
                opttexturer = new TextureRegion(opttexture);
                opttexturerd = new TextureRegionDrawable(opttexturer);
                optbutton = new ImageButton(opttexturerd);
                stage.addActor(optbutton);
            }

            stage.addActor(playbutton);
            stage.addActor(scorebutton);
            stage.addActor(exitbutton);
            Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show(){
        if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
            create();
            playbutton.setPosition(0, height / 2f - 100);
            playbutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);
            if (TrJr.INSTANCE.getScrW()>=1080) {
                optbutton.setPosition(0, TrJr.INSTANCE.getScrH() / 24f);

                scorebutton.setPosition(-20, TrJr.INSTANCE.getScrH()/24f+250);
                scorebutton.setSize(TrJr.INSTANCE.getScrW()/4f,TrJr.INSTANCE.getScrH()/12f);

                exitbutton.setPosition(width - 250, TrJr.INSTANCE.getScrH()/24f);
            }
            else {
                optbutton.setPosition(0, TrJr.INSTANCE.getScrH() / 12f);
                optbutton.setSize(TrJr.INSTANCE.getScrW() / 4f, TrJr.INSTANCE.getScrH() / 12f);

                scorebutton.setPosition(-20, TrJr.INSTANCE.getScrH() / 12f); //*2.5f
                scorebutton.setSize(TrJr.INSTANCE.getScrW() / 4f, TrJr.INSTANCE.getScrH() / 12f);

                exitbutton.setPosition(width - TrJr.INSTANCE.getScrW()/4f+20, TrJr.INSTANCE.getScrH()/12f);
                exitbutton.setSize(TrJr.INSTANCE.getScrW()/4f,TrJr.INSTANCE.getScrH()/12f);
            }
            playbutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                    Gdx.input.setInputProcessor(null);
                    if (launch) TrJr.INSTANCE.setScreen(new TutorialScreen(camera));
                    else TrJr.INSTANCE.setScreen(new GameScreen(camera));
                }
            });

            if (TrJr.INSTANCE.getScrW()>=1080) {
            optbutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                    Gdx.input.setInputProcessor(null);
                    TrJr.INSTANCE.setScreen(new OptionsScreen(camera));
                }
            });
            }


            scorebutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (settings.isSoundOn()) Assets.playSound(Assets.blip2);
                }
            });

            exitbutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Gdx.app.exit();
                }
            });

    }

    public void update(){
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
    }

    @Override
    public void render(float delta){
        update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (TrJr.INSTANCE.getScrW()<1080) gamelogos.setPosition(TrJr.INSTANCE.getScrW()/2f-282, TrJr.INSTANCE.getScrH()/6f*3.5f);
        else {
            gamelogos.setPosition(TrJr.INSTANCE.getScrW()/2f-282, TrJr.INSTANCE.getScrH()-512);
        }
        gamelogos.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void pause(){

    }

    @Override
    public void dispose(){

    }

    @Override
    public void hide(){

    }

}
