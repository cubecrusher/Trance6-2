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
    private String rusdifftext;
    private int height = TrJr.INSTANCE.getScrH();
    private int width = TrJr.INSTANCE.getScrW();
    private Settings settings;
    Vector3 touchpt = new Vector3();
    int n=0;

    public DifficultyScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        this.camera.position.set(new Vector3(width/2f, height/2f,0));
        this.batch = new SpriteBatch();
        this.nameset = settings.getNameSet();
        if (settings.getDifficulty().equals("Beginner")) rusdifftext = "Новичок";
        if (settings.getDifficulty().equals("Medium")) rusdifftext = "Нормальная";
        if (settings.getDifficulty().equals("Expert")) rusdifftext = "Эксперт";
        if (settings.getDifficulty().equals("Cursed")) rusdifftext = "Хардкор";
    }

    public void create(){
        Texture difficultyt = new Texture(Gdx.files.internal("textures/difficulty.png"));
        difficulty = new Sprite(difficultyt);
        Texture easytexture = new Texture(Gdx.files.internal("textures/new/deasy.png"));
        Texture normaltexture = new Texture(Gdx.files.internal("textures/new/dnormal.png"));
        Texture hardtexture = new Texture(Gdx.files.internal("textures/new/dhard.png"));
        Texture cursedtexture = new Texture(Gdx.files.internal("textures/new/dxhard.png"));
        Texture backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));
        Texture reasytexture = new Texture(Gdx.files.internal("textures/new/rdeasy.png"));
        Texture rnormaltexture = new Texture(Gdx.files.internal("textures/new/rdnormal.png"));
        Texture rhardtexture = new Texture(Gdx.files.internal("textures/new/rdhard.png"));
        Texture rcursedtexture = new Texture(Gdx.files.internal("textures/new/rdxhard.png"));

        if (settings.getLanguage()==1){
            easytexturer = new TextureRegion(reasytexture);
            normaltexturer = new TextureRegion(rnormaltexture);
            hardtexturer = new TextureRegion(rhardtexture);
            cursedtexturer = new TextureRegion(rcursedtexture);
        } else {
            easytexturer = new TextureRegion(easytexture);
            normaltexturer = new TextureRegion(normaltexture);
            hardtexturer = new TextureRegion(hardtexture);
            cursedtexturer = new TextureRegion(cursedtexture);
        }
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

        if (width>=1080) {
            easybutton.setPosition(-5, height / 2f + 75);
            easybutton.setSize(width-80,height/12f);

            normalbutton.setPosition(-5, height / 2f - 100);
            normalbutton.setSize(width-80,height/12f);

            hardbutton.setPosition(-5, height / 2f - 275);
            hardbutton.setSize(width-80,height/12f);

            cursedbutton.setPosition(-5, height / 2f - 450);
            cursedbutton.setSize(width-80,height/12f);

            backbutton.setPosition(0, height / 24f);
        } else {
            easybutton.setPosition(-5, height / 2f);
            easybutton.setSize(width-80,height/12f);

            normalbutton.setPosition(-5, height / 2f - 100);
            normalbutton.setSize(width-80,height/12f);

            hardbutton.setPosition(-5, height / 2f - 200);
            hardbutton.setSize(width-80,height/12f);

            cursedbutton.setPosition(-5, height / 2f - 300);
            cursedbutton.setSize(width-80,height/12f);

            backbutton.setPosition(-20, height / 12f);
            backbutton.setSize(width / 4f, height / 12f);
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

        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(0, height-height/25f, width, 10);
        shapeRenderer.end();

        if (n<=2) {
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(0, 0, width, height);
            shapeRenderer.end();
            n++;
        }
        batch.begin();
        if (width<1080) {
            difficulty.setPosition(width/2f-284, height/6f*4.5f);
            if (settings.getLanguage()==1)
                TrJr.INSTANCE.rfont3.draw(batch, "Сложность: "+rusdifftext, 20, height / 2f + 135);
            else
                TrJr.INSTANCE.font3.draw(batch, "Difficulty: "+settings.getDifficulty(), 20, height / 2f + 135);

            TrJr.INSTANCE.fontCyan3.draw(batch, "$", 15, height - 14);
            TrJr.INSTANCE.font3.draw(batch, ""+settings.getMoney(), 35, height - 14);
        }
        else {
            difficulty.setPosition(width/2f-284, height-512);
            if (settings.getLanguage()==1)
                TrJr.INSTANCE.rfont2.draw(batch, "Сложность: "+rusdifftext, 25, height / 2f + 300);
            else
                TrJr.INSTANCE.font2.draw(batch, "Difficulty: "+settings.getDifficulty(), 25, height / 2f + 300);

            TrJr.INSTANCE.fontCyan2.draw(batch, "$ ", 20, height - 28);
            TrJr.INSTANCE.font2.draw(batch, ""+settings.getMoney(), 55, height - 28);
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
