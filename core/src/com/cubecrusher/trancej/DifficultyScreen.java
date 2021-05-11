package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
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

public class DifficultyScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Stage stage;
    private TextureRegion easytexturer, normaltexturer, hardtexturer, cursedtexturer, backtexturer;
    private TextureRegionDrawable easytexturerd, normaltexturerd, hardtexturerd, cursedtexturerd,  backtexturerd;
    private ImageButton easybutton, normalbutton, hardbutton, cursedbutton, backbutton;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite difficulty;
    private boolean nameset;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private Settings settings;
    Vector3 touchpt = new Vector3();
    int n=0;

    public DifficultyScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.batch = new SpriteBatch();
        this.nameset = settings.getNameSet();
    }

    public void create(){
        Texture difficultyt = new Texture(Gdx.files.internal("textures/difficulty.png"));
        difficulty = new Sprite(difficultyt);
        Texture easytexture = new Texture(Gdx.files.internal("textures/new/deasy.png"));
        Texture normaltexture = new Texture(Gdx.files.internal("textures/new/dnormal.png"));
        Texture hardtexture = new Texture(Gdx.files.internal("textures/new/dhard.png"));
        Texture cursedtexture = new Texture(Gdx.files.internal("textures/new/dxhard.png"));
        Texture backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));


        easytexturer = new TextureRegion(easytexture);
        normaltexturer = new TextureRegion(normaltexture);
        hardtexturer = new TextureRegion(hardtexture);
        cursedtexturer = new TextureRegion(cursedtexture);
        backtexturer = new TextureRegion(backtexture);

        easytexturerd = new TextureRegionDrawable(easytexturer);
        normaltexturerd = new TextureRegionDrawable(normaltexturer);
        hardtexturerd = new TextureRegionDrawable(hardtexturer);
        cursedtexturerd = new TextureRegionDrawable(cursedtexturer);
        backtexturerd = new TextureRegionDrawable(backtexturer);

        easybutton = new ImageButton(easytexturerd);
        normalbutton = new ImageButton(normaltexturerd);
        hardbutton = new ImageButton(hardtexturerd);
        cursedbutton = new ImageButton(cursedtexturerd);
        backbutton = new ImageButton(backtexturerd);

        stage = new Stage(new ScreenViewport());

        stage.addActor(easybutton);
        stage.addActor(normalbutton);
        stage.addActor(hardbutton);
        stage.addActor(cursedbutton);
        stage.addActor(backbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show(){
        if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        create();

        easybutton.setPosition(-5, height / 2f + 75);
        easybutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);

        normalbutton.setPosition(-5, height / 2f - 100);
        normalbutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);

        hardbutton.setPosition(-5, height / 2f - 275);
        hardbutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);

        cursedbutton.setPosition(-5, height / 2f - 450);
        cursedbutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);

        if (TrJr.INSTANCE.getScrW()>=1080) {
            backbutton.setPosition(0, TrJr.INSTANCE.getScrH() / 24f);
        } else {
            backbutton.setPosition(-20, TrJr.INSTANCE.getScrH() / 12f);
            backbutton.setSize(TrJr.INSTANCE.getScrW() / 4f, TrJr.INSTANCE.getScrH() / 12f);
        }

        easybutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                settings.setDifficulty("Beginner");
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new MainScreen(camera));
            }
        });
        normalbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                settings.setDifficulty("Medium");
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new MainScreen(camera));
            }
        });

        hardbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                settings.setDifficulty("Expert");
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new MainScreen(camera));
            }
        });

        cursedbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                settings.setDifficulty("Cursed");
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new MainScreen(camera));
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
        if (n<=2) {
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(0, 0, width, height);
            shapeRenderer.end();
            n++;
        }
        batch.begin();
        if (TrJr.INSTANCE.getScrW()<1080) {
            difficulty.setPosition(TrJr.INSTANCE.getScrW()/2f-284, TrJr.INSTANCE.getScrH()/6f*3.5f);
            TrJr.INSTANCE.font3.draw(batch, "Difficulty: "+settings.getDifficulty(), 20, height / 2f + 200);
        }
        else {
            difficulty.setPosition(TrJr.INSTANCE.getScrW()/2f-284, TrJr.INSTANCE.getScrH()-512);
            TrJr.INSTANCE.font2.draw(batch, "Difficulty: "+settings.getDifficulty(), 25, height / 2f + 300);
        }
        difficulty.draw(batch);
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
