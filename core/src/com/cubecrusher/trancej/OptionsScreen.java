package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OptionsScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Stage stage;
    private Settings settings;
    Texture optionstextt;
    Texture soundtexture;
    Texture musictexture;
    Texture backtexture;
    Texture creditstexture;
    private TextureRegion soundtexturer, musictexturer, backtexturer, creditstexturer;
    private TextureRegionDrawable soundtexturerd, musictexturerd, backtexturerd, creditstexturerd;
    private ImageButton soundbutton, musicbutton, backbutton, creditsbutton;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private Sprite optionstext;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private int spritey = 0;
    int n = 0;


    public OptionsScreen(OrthographicCamera camera){
        this.camera = camera;
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.settings = new Settings();
    }

    public void create(){
        {
            optionstextt = new Texture(Gdx.files.internal("textures/optionstext.png"));
            optionstext = new Sprite(optionstextt);
            soundtexture = new Texture(Gdx.files.internal("textures/new/sound.png"));
            musictexture = new Texture(Gdx.files.internal("textures/new/music.png"));
            backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));
            creditstexture = new Texture(Gdx.files.internal("textures/new/credits.png"));

            soundtexturer = new TextureRegion(soundtexture);
            musictexturer = new TextureRegion(musictexture);
            backtexturer = new TextureRegion(backtexture);
            creditstexturer = new TextureRegion(creditstexture);

            soundtexturerd = new TextureRegionDrawable(soundtexturer);
            musictexturerd = new TextureRegionDrawable(musictexturer);
            backtexturerd = new TextureRegionDrawable(backtexturer);
            creditstexturerd = new TextureRegionDrawable(creditstexturer);

            soundbutton = new ImageButton(soundtexturerd);
            musicbutton = new ImageButton(musictexturerd);
            backbutton = new ImageButton(backtexturerd);
            creditsbutton = new ImageButton(creditstexturerd);

            stage = new Stage(new ScreenViewport());
            stage.addActor(soundbutton);
            stage.addActor(musicbutton);
            stage.addActor(backbutton);
            stage.addActor(creditsbutton);
            Gdx.input.setInputProcessor(stage);
        }
    }

    @Override
    public void show(){
        create();
        if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        soundbutton.setPosition(-20, height / 2f - 100 + TrJr.INSTANCE.getScrH()/12f);
        soundbutton.setSize(TrJr.INSTANCE.getScrW()/4f,TrJr.INSTANCE.getScrH()/12f);
        musicbutton.setPosition(-20, height / 2f - 100 - TrJr.INSTANCE.getScrH()/12f);
        musicbutton.setSize(TrJr.INSTANCE.getScrW()/4f,TrJr.INSTANCE.getScrH()/12f);
        if (TrJr.INSTANCE.getScrW()>=1080) {
            backbutton.setPosition(0, TrJr.INSTANCE.getScrH() / 24f);
            creditsbutton.setPosition(width - 250, TrJr.INSTANCE.getScrH()/24f);
        } else {
            backbutton.setPosition(-20, TrJr.INSTANCE.getScrH() / 12f);
            backbutton.setSize(TrJr.INSTANCE.getScrW() / 4f, TrJr.INSTANCE.getScrH() / 12f);
            creditsbutton.setPosition(width - TrJr.INSTANCE.getScrW()/4f+20, TrJr.INSTANCE.getScrH()/12f);
            creditsbutton.setSize(TrJr.INSTANCE.getScrW()/4f,TrJr.INSTANCE.getScrH()/12f);
        }
        soundbutton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {

                settings.setSound(!settings.isSoundOn());
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);

            }

        });

        musicbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                settings.setMusic(!settings.isMusicOn());
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);

            }
        });

        backbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new MainScreen(camera));
            }
        });

        creditsbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new CreditsScreen(camera));
            }
        });
    }

    public void update(){
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
        if(settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        else Assets.stopMusic(Assets.mainMenu);
        if (spritey < TrJr.INSTANCE.getScrH()/4.5f) spritey+=25;
    }

    @Override
    public void render(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            Gdx.input.setCatchKey(Input.Keys.BACK,true);
            TrJr.INSTANCE.setScreen(new MainScreen(camera));
        }
        update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (n<=2) {
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(0, 0, width, height);
            shapeRenderer.end();
            n++;
        }

        batch.begin();
        optionstext.setPosition(TrJr.INSTANCE.getScrW()/2f-234, 0.8f*height);
        optionstext.draw(batch);
        if (TrJr.INSTANCE.getScrW()<1080) {
            if (settings.isSoundOn())
                TrJr.INSTANCE.font2.draw(batch, "Sound ON", TrJr.INSTANCE.getScrW() / 5f * 2, height / 2f - 100 + TrJr.INSTANCE.getScrH() / 12f * 2 - 25);
            else
                TrJr.INSTANCE.font2.draw(batch, "Sound OFF", TrJr.INSTANCE.getScrW() / 5f * 2, height / 2f - 100 + TrJr.INSTANCE.getScrH() / 12f * 2 - 25);

            if (settings.isMusicOn())
                TrJr.INSTANCE.font2.draw(batch, "Music ON", TrJr.INSTANCE.getScrW() / 5f * 2, height / 2f + 100 - TrJr.INSTANCE.getScrH() / 12f * 2 - 25);
            else
                TrJr.INSTANCE.font2.draw(batch, "Music OFF", TrJr.INSTANCE.getScrW() / 5f * 2, height / 2f + 100 - TrJr.INSTANCE.getScrH() / 12f * 2 - 25);
        }
        else {
            if (settings.isSoundOn())
                TrJr.INSTANCE.font.draw(batch, "Sound ON", TrJr.INSTANCE.getScrW() / 6f * 2, height / 2f + 210);
            else
                TrJr.INSTANCE.font.draw(batch, "Sound OFF", TrJr.INSTANCE.getScrW() / 6f * 2, height / 2f + 210);

            if (settings.isMusicOn())
                TrJr.INSTANCE.font.draw(batch, "Music ON", TrJr.INSTANCE.getScrW() / 6f * 2, height / 2f - 180);
            else
                TrJr.INSTANCE.font.draw(batch, "Music OFF", TrJr.INSTANCE.getScrW() / 6f * 2, height / 2f - 180);
        }
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
