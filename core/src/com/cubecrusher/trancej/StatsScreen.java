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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StatsScreen extends ScreenAdapter {
    public static float time;
    public static boolean newBest = false;
    private OrthographicCamera camera;
    protected GameScreen gameScreen;
    private Viewport viewport;
    private Stage stage;
    private TextureRegion scoretexturer, backtexturer;
    private TextureRegionDrawable scoretexturerd, backtexturerd;
    private ImageButton scorebutton, backbutton;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite stats;
    private int height = TrJr.INSTANCE.getScrH();
    private int width = TrJr.INSTANCE.getScrW();
    private Settings settings;
    private int n = 0;

    // Экран статистики. Показывается... статистика.
    // Настройки из рекордов, никнейма, попыток, среднего времени, тд тп

    public StatsScreen(OrthographicCamera camera) {
        this.settings = new Settings();
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        this.camera.position.set(new Vector3(width / 2f, height / 2f, 0));
        this.viewport = new FitViewport(800, 400, camera);
        this.batch = new SpriteBatch();
    }

    public void create(){
        Texture scoretexture = new Texture(Gdx.files.internal("textures/new/scores.png"));
        Texture statss = new Texture(Gdx.files.internal("textures/stats.png"));
        stats = new Sprite(statss);
        Texture backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));

        scoretexturer = new TextureRegion(scoretexture);
        scoretexturerd = new TextureRegionDrawable(scoretexturer);
        scorebutton = new ImageButton(scoretexturerd);


        backtexturer = new TextureRegion(backtexture);

        backtexturerd = new TextureRegionDrawable(backtexturer);

        backbutton = new ImageButton(backtexturerd);

        stage = new Stage(new ScreenViewport());
        stage.addActor(scorebutton);
        stage.addActor(backbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show(){
        create();
        if (width>=1080) {
            scorebutton.setPosition(-20, height/24f+150);
            scorebutton.setSize(width/4f,height/12f);
            backbutton.setPosition(0, height / 24f);
        } else {
            scorebutton.setPosition(-20, height / 12f * 2.5f);
            scorebutton.setSize(width / 4f, height / 12f);
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

        scorebutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new ScoreScreen(camera));
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


        stats.setPosition(width / 2f - 193, height - height / 4.5f);
        stats.draw(batch);
        if (settings.getLanguage()==1){
            if (width < 1080) {
                TrJr.INSTANCE.rfontCyan3.draw(batch, "Никнейм: " + settings.getUsername(), width / 20f, height / 3f * 2);
                TrJr.INSTANCE.rfont3.draw(batch, "Попыток: " + settings.getPlays(), width / 20f, height / 3f * 2 - 45);
                TrJr.INSTANCE.rfont3.draw(batch, "Рекорд (Новичок): " + settings.geteHighScore(), width / 20f, height / 3f * 2 - 90);
                TrJr.INSTANCE.rfont3.draw(batch, "Рекорд (Нормальная): " + settings.getnHighScore(), width / 20f, height / 3f * 2 - 135);
                TrJr.INSTANCE.rfont3.draw(batch, "Рекорд (Эксперт): " + settings.gethHighScore(), width / 20f, height / 3f * 2 - 180);
                TrJr.INSTANCE.rfont3.draw(batch, "Рекорд (Хардкор): " + settings.getcHighScore(), width / 20f, height / 3f * 2 - 225);
                TrJr.INSTANCE.rfont3.draw(batch, "Среднее время: " + ((int) (settings.getTotal() / settings.getPlays() * 100)) / 100f, width / 20f, height / 3f * 2 - 270);
                TrJr.INSTANCE.rfont3.draw(batch, "Общее время: " + settings.getTotal(), width / 20f, height / 3f * 2 - 315);

                TrJr.INSTANCE.fontCyan3.draw(batch, "$", 15, height - 14);
                TrJr.INSTANCE.font3.draw(batch, ""+settings.getMoney(), 35, height - 14);
            } else {
                TrJr.INSTANCE.rfontCyan2.draw(batch, "Никнейм: " + settings.getUsername(), width / 20f, height / 3f * 2);
                TrJr.INSTANCE.rfont2.draw(batch, "Попыток: " + settings.getPlays(), width / 20f, height / 3f * 2 - 50);
                TrJr.INSTANCE.rfont2.draw(batch, "Рекорд (Новичок): " + settings.geteHighScore(), width / 20f, height / 3f * 2 - 100);
                TrJr.INSTANCE.rfont2.draw(batch, "Рекорд (Нормальная): " + settings.getnHighScore(), width / 20f, height / 3f * 2 - 150);
                TrJr.INSTANCE.rfont2.draw(batch, "Рекорд (Эксперт): " + settings.gethHighScore(), width / 20f, height / 3f * 2 - 200);
                TrJr.INSTANCE.rfont2.draw(batch, "Рекорд (Хардкор): " + settings.getcHighScore(), width / 20f, height / 3f * 2 - 250);
                TrJr.INSTANCE.rfont2.draw(batch, "Среднее время: " + ((int) (settings.getTotal() / settings.getPlays() * 100)) / 100f, width / 20f, height / 3f * 2 - 300);
                TrJr.INSTANCE.rfont2.draw(batch, "Общее время: " + settings.getTotal(), width / 20f, height / 3f * 2 - 350);

                TrJr.INSTANCE.fontCyan2.draw(batch, "$ ", 20, height - 28);
                TrJr.INSTANCE.font2.draw(batch, "" + settings.getMoney(), 55, height - 28);
            }
        } else {
            if (width < 1080) {
                TrJr.INSTANCE.fontCyan3.draw(batch, "Nickname: " + settings.getUsername(), width / 20f, height / 3f * 2);
                TrJr.INSTANCE.font3.draw(batch, "Attempts: " + settings.getPlays(), width / 20f, height / 3f * 2 - 45);
                TrJr.INSTANCE.font3.draw(batch, "Best (Beginner): " + settings.geteHighScore(), width / 20f, height / 3f * 2 - 90);
                TrJr.INSTANCE.font3.draw(batch, "Best (Medium): " + settings.getnHighScore(), width / 20f, height / 3f * 2 - 135);
                TrJr.INSTANCE.font3.draw(batch, "Best (Expert): " + settings.gethHighScore(), width / 20f, height / 3f * 2 - 180);
                TrJr.INSTANCE.font3.draw(batch, "Best (Cursed): " + settings.getcHighScore(), width / 20f, height / 3f * 2 - 225);
                TrJr.INSTANCE.font3.draw(batch, "Average: " + ((int) (settings.getTotal() / settings.getPlays() * 100)) / 100f, width / 20f, height / 3f * 2 - 270);
                TrJr.INSTANCE.font3.draw(batch, "Total: " + settings.getTotal(), width / 20f, height / 3f * 2 - 315);

                TrJr.INSTANCE.fontCyan3.draw(batch, "$", 15, height - 14);
                TrJr.INSTANCE.font3.draw(batch, ""+settings.getMoney(), 35, height - 14);
            } else {
                TrJr.INSTANCE.fontCyan2.draw(batch, "Nickname: " + settings.getUsername(), width / 20f, height / 3f * 2);
                TrJr.INSTANCE.font2.draw(batch, "Attempts: " + settings.getPlays(), width / 20f, height / 3f * 2 - 50);
                TrJr.INSTANCE.font2.draw(batch, "Best (Beginner): " + settings.geteHighScore(), width / 20f, height / 3f * 2 - 100);
                TrJr.INSTANCE.font2.draw(batch, "Best (Medium): " + settings.getnHighScore(), width / 20f, height / 3f * 2 - 150);
                TrJr.INSTANCE.font2.draw(batch, "Best (Expert): " + settings.gethHighScore(), width / 20f, height / 3f * 2 - 200);
                TrJr.INSTANCE.font2.draw(batch, "Best (Cursed): " + settings.getcHighScore(), width / 20f, height / 3f * 2 - 250);
                TrJr.INSTANCE.font2.draw(batch, "Average: " + ((int) (settings.getTotal() / settings.getPlays() * 100)) / 100f, width / 20f, height / 3f * 2 - 300);
                TrJr.INSTANCE.font2.draw(batch, "Total: " + settings.getTotal(), width / 20f, height / 3f * 2 - 350);

                TrJr.INSTANCE.fontCyan2.draw(batch, "$ ", 20, height - 28);
                TrJr.INSTANCE.font2.draw(batch, "" + settings.getMoney(), 55, height - 28);
            }
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
