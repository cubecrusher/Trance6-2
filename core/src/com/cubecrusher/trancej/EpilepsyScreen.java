package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class EpilepsyScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    protected MainScreen mainScreen;
    private SpriteBatch batch;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private Settings settings;
    int n=0;


    public EpilepsyScreen(OrthographicCamera camera){
        this.camera = camera;
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.mainScreen = new MainScreen(camera);
        this.batch = new SpriteBatch();
    }

    public void create(){

    }

    @Override
    public void show(){

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
        n++;

        if (TrJr.INSTANCE.getScrW() < 1080) {
            TrJr.INSTANCE.font.draw(batch, "WARNING!", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 200);
            TrJr.INSTANCE.font3.draw(batch, "This game contains flashing lights.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 100);
            TrJr.INSTANCE.font3.draw(batch, "Discretion is advised.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 50);
        } else {
            TrJr.INSTANCE.fontCyan.draw(batch, "WARNING!", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 200);
            TrJr.INSTANCE.font2.draw(batch, "This game contains flashing lights.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 100);
            TrJr.INSTANCE.font2.draw(batch, "Discretion is advised.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 25);
        }
        batch.end();

        if (n>=120) TrJr.INSTANCE.setScreen(new MainScreen(camera));
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

