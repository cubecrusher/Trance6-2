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

public class StatsScreen extends ScreenAdapter {
    public static float time;
    public static boolean newBest = false;
    private OrthographicCamera camera;
    protected GameScreen gameScreen;
    private Stage stage;
    private TextureRegion backtexturer;
    private TextureRegionDrawable backtexturerd;
    private ImageButton backbutton;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite stats;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private Settings settings;
    private int n = 0;


    public StatsScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.batch = new SpriteBatch();
    }

    public void create(){
        Texture statss = new Texture(Gdx.files.internal("textures/stats.png"));
        stats = new Sprite(statss);
        Texture backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));


        backtexturer = new TextureRegion(backtexture);

        backtexturerd = new TextureRegionDrawable(backtexturer);

        backbutton = new ImageButton(backtexturerd);

        stage = new Stage(new ScreenViewport());
        stage.addActor(backbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show(){
        create();
        if (TrJr.INSTANCE.getScrW()>=1080) {
            backbutton.setPosition(0, height / 24f);
        } else {
            backbutton.setPosition(-20, height / 12f);
            backbutton.setSize(width / 4f, height / 12f);
        }
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
        if (n<=2) {
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(0, 0, width, height);
            shapeRenderer.end();
            n++;
        }
        stats.setPosition(width / 2f - 193, height - TrJr.INSTANCE.getScrH() / 4.5f);
        stats.draw(batch);

        if (width < 1080) {
                Assets.guiSmall.draw(batch, "Plays: " + settings.getPlays(), width / 20f, height / 3f * 2);
                Assets.guiSmall.draw(batch, "Best: " + settings.getHighScore(), width / 20f, height / 3f * 2 - 45);
                Assets.guiSmall.draw(batch, "Average: " + ((int)(settings.getTotal()/settings.getPlays()*100))/100f, width / 20f, height / 3f * 2 - 90);
                Assets.guiSmall.draw(batch, "Total: " + settings.getTotal(), width / 20f, height / 3f * 2 - 135);
        } else {
                Assets.guiSmall.draw(batch, "Plays: " + settings.getPlays(), width / 20f, height / 3f * 2);
                Assets.guiSmall.draw(batch, "Best: " + settings.getHighScore(), width / 20f, height / 3f * 2 - 50);
                Assets.guiSmall.draw(batch, "Average: " + ((int)(settings.getTotal()/settings.getPlays()*100))/100f, width / 20f, height / 3f * 2 - 100);
                Assets.guiSmall.draw(batch, "Total: " + settings.getTotal(), width / 20f, height / 3f * 2 - 150);
        }
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose(){

    }

    @Override
    public void hide(){

    }

}
