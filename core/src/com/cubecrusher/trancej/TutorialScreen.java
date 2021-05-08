package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

public class TutorialScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Viewport viewport;
    protected GameScreen gameScreen;
    private Stage stage;
    private TextureRegion playtexturer;
    private TextureRegionDrawable playtexturerd;
    private ImageButton playbutton;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private Settings settings;
    int n=0;


    public TutorialScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.viewport = new FitViewport(800,400, camera);
        this.gameScreen = new GameScreen(camera);
        this.shapeRenderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
    }

    public void create(){
        //if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        Texture playtexture = new Texture(Gdx.files.internal("textures/new/play.png"));


        playtexturer = new TextureRegion(playtexture);
        playtexturerd = new TextureRegionDrawable(playtexturer);
        playbutton = new ImageButton(playtexturerd);

        stage = new Stage(new ScreenViewport());
        stage.addActor(playbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show(){
        create();
        playbutton.setPosition(0, height / 2f - 200);
        playbutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);

        playbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) {
                    Assets.playSound(Assets.blip1);
                    Assets.stopMusic(Assets.mainMenu);
                }
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new GameScreen(camera));
                settings.setLaunch(false);
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
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(0, 0, width, height);
            shapeRenderer.end();
            n++;
        }

        if (TrJr.INSTANCE.getScrW() < 1080) {
                TrJr.INSTANCE.font2.draw(batch, "In this game, you dodge.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
                TrJr.INSTANCE.font2.draw(batch, "Avoid obstacles.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 175);
                TrJr.INSTANCE.font2.draw(batch, "Stay in the lower half.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 125);
                TrJr.INSTANCE.font2.draw(batch, "The longer - the better.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 75);
                TrJr.INSTANCE.font2.draw(batch, "Have fun.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 25);
        } else {
                TrJr.INSTANCE.font2.draw(batch, "In Trance Journey, you dodge.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 375);
                TrJr.INSTANCE.font2.draw(batch, "Move your finger to move the ship.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 325);
                TrJr.INSTANCE.font2.draw(batch, "Stay in the lower half.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 275);
                TrJr.INSTANCE.font2.draw(batch, "Survive as long as you can.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
                TrJr.INSTANCE.font2.draw(batch, "Have fun.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 175);
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

