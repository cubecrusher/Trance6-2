package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
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
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

public class ScoreScreen extends ScreenAdapter implements Net.HttpResponseListener {
    private OrthographicCamera camera;
    protected GameScreen gameScreen;
    private Stage stage;
    private TextureRegion backtexturer;
    private TextureRegionDrawable backtexturerd;
    private ImageButton backbutton;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private Sprite scores;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private Settings settings;
    int n=0;


    public ScoreScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.gameScreen = new GameScreen(camera);
        this.shapeRenderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
    }

    public void create(){
        //if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        Texture backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));
        Texture scoress = new Texture(Gdx.files.internal("textures/scorestext.png"));
        scores = new Sprite(scoress);

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
                TrJr.INSTANCE.setScreen(new StatsScreen(camera));
            }
        });
    }

    public void update(){
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
    }

    public void getScores(){
        StringBuilder urlReq = new StringBuilder("http://dreamlo.com/lb/RgmW1USbOUGLxputvY42UgxmTCP95THkW4TfGUvJItLw/json/");
        String urlString = urlReq.toString();
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlString).build();
        Gdx.net.sendHttpRequest(httpRequest, this);
    }

    public void parseScores(String scoreString){

        ArrayList<String> highscoreList = new ArrayList<>();
        JsonReader json = new JsonReader();
        JsonValue base = json.parse(scoreString);
        JsonValue dlo = base.get("dreamlo");
        JsonValue lb = dlo.get("leaderboard");
        JsonValue entries = lb.get("entry");
        for (int i=0; i<10; i++){
            int rank = i+1;
            JsonValue score = entries.get(i);
            String value = score.getString("score");
            highscoreList.add("" + rank + ". " + value + "s");
        }
        if (TrJr.INSTANCE.getScrW() < 1080) {
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(0), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(1), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 175);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(2), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 125);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(3), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 75);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(4), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 25);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(5), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 25);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(6), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 75);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(7), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 125);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(8), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 175);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(9), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 225);
        } else {
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(0), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 375);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(1), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 325);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(2), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 275);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(3), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(4), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 175);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(5), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 125);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(6), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 75);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(7), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 25);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(8), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 25);
            TrJr.INSTANCE.font2.draw(batch, highscoreList.get(9), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 75);
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
        if (n<=2) {
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(0, 0, width, height);
            shapeRenderer.end();
            n++;
        }
        batch.begin();
        scores.setPosition(width / 2f - 224, height - TrJr.INSTANCE.getScrH() / 4.5f);
        scores.draw(batch);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.end();
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

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {

    }

    @Override
    public void failed(Throwable t) {

    }

    @Override
    public void cancelled() {

    }
}

