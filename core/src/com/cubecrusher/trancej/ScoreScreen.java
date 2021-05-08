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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class ScoreScreen extends ScreenAdapter {
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
    private Viewport viewport;
    int n=0, colored=10;
    boolean a=true, isDone = false, hasfailed = false;
    public ArrayList<String> highscoreList;


    public ScoreScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.viewport = new FitViewport(800,400, camera);
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.gameScreen = new GameScreen(camera);
        this.shapeRenderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.highscoreList = new ArrayList<>();
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

    public void getScores() {
        String urlString = "http://dreamlo.com/lb/RgmW1USbOUGLxputvY42UgxmTCP95THkW4TfGUvJItLw/json/";
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlString).build();
        if (a) {
            Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    System.out.println("(!!!) network RESPONSE: ");
                    JsonReader json = new JsonReader();
                    String results = httpResponse.getResultAsString();
                    System.out.println("STRING: " + results);
                        JsonValue base = json.parse(results);
                        JsonValue dlo = base.get("dreamlo");
                        JsonValue lb = dlo.get("leaderboard");
                        JsonValue entries = lb.get("entry");
                        for (int i = 0; i < 10; i++) {
                            JsonValue score = entries.get(i);
                            String name = score.getString("name");
                            if (name.equals(settings.getUsername())) colored=i;
                            String value = score.getString("score");
                            int intPart = Integer.parseInt(value) / 100;
                            float floatPart = Float.parseFloat(value) % 100;
                            value = intPart + "." + floatPart;
                            value = value.substring(0, value.length() - 2);
                            highscoreList.add(name + "  -  " + value);
                            if (i == 9) isDone = true;
                        }
                }

                @Override
                public void failed(Throwable t) {
                    System.out.println("(!!!) submitScores() FAILED: ");
                    t.printStackTrace();
                    hasfailed=true;
                }

                @Override
                public void cancelled() {

                }
            });
        }
    }

    @Override
    public void render(float delta) {
        if (a) {
            getScores();
            a=false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            Gdx.input.setCatchKey(Input.Keys.BACK,true);
            TrJr.INSTANCE.setScreen(new StatsScreen(camera));
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
        if (isDone) {
            if (TrJr.INSTANCE.getScrW() < 1080) {
                TrJr.INSTANCE.font3.draw(batch, "1.    "+ highscoreList.get(0), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
                TrJr.INSTANCE.font3.draw(batch, "2.    "+ highscoreList.get(1), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 175);
                TrJr.INSTANCE.font3.draw(batch, "3.    "+ highscoreList.get(2), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 125);
                TrJr.INSTANCE.font3.draw(batch, "4.    "+ highscoreList.get(3), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 75);
                TrJr.INSTANCE.font3.draw(batch, "5.    "+ highscoreList.get(4), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 25);
                TrJr.INSTANCE.font3.draw(batch, "6.    "+ highscoreList.get(5), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 25);
                TrJr.INSTANCE.font3.draw(batch, "7.    "+ highscoreList.get(6), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 75);
                TrJr.INSTANCE.font3.draw(batch, "8.    "+ highscoreList.get(7), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 125);
                TrJr.INSTANCE.font3.draw(batch, "9.    "+ highscoreList.get(8), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 175);
                TrJr.INSTANCE.font3.draw(batch, "10.   "+ highscoreList.get(9), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 225);
                if (settings.getNameSet()) TrJr.INSTANCE.font3.draw(batch, "Your nickname: "+settings.getUsername(), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 375);
            } else {
                // Это действительно тупо, но я делал это в 2 ночи, прошу прощения
                if (colored==0) TrJr.INSTANCE.fontCyan2.draw(batch, "0.    "+ highscoreList.get(0), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 375);
                else TrJr.INSTANCE.font2.draw(batch, "0.    "+ highscoreList.get(0), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 375);
                if (colored==1) TrJr.INSTANCE.fontCyan2.draw(batch, "2.    "+ highscoreList.get(1), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 325);
                else TrJr.INSTANCE.font2.draw(batch, "2.    "+ highscoreList.get(1), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 325);
                if (colored==2) TrJr.INSTANCE.fontCyan2.draw(batch, "3.    "+ highscoreList.get(2), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 275);
                else TrJr.INSTANCE.font2.draw(batch, "3.    "+ highscoreList.get(2), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 275);
                if (colored==3) TrJr.INSTANCE.fontCyan2.draw(batch, "4.    "+ highscoreList.get(3), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
                else TrJr.INSTANCE.font2.draw(batch, "4.    "+ highscoreList.get(3), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
                if (colored==4) TrJr.INSTANCE.fontCyan2.draw(batch, "5.    "+ highscoreList.get(4), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 175);
                else TrJr.INSTANCE.font2.draw(batch, "5.    "+ highscoreList.get(4), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 175);
                if (colored==5) TrJr.INSTANCE.fontCyan2.draw(batch, "6.    "+ highscoreList.get(5), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 125);
                else TrJr.INSTANCE.font2.draw(batch, "6.    "+ highscoreList.get(5), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 125);
                if (colored==6) TrJr.INSTANCE.fontCyan2.draw(batch, "7.    "+ highscoreList.get(6), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 75);
                else TrJr.INSTANCE.font2.draw(batch, "7.    "+ highscoreList.get(6), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 75);
                if (colored==7) TrJr.INSTANCE.fontCyan2.draw(batch, "8.    "+ highscoreList.get(7), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 25);
                else TrJr.INSTANCE.font2.draw(batch, "8.    "+ highscoreList.get(7), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 25);
                if (colored==8) TrJr.INSTANCE.fontCyan2.draw(batch, "9.    "+ highscoreList.get(8), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 25);
                else TrJr.INSTANCE.font2.draw(batch, "9.    "+ highscoreList.get(8), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 25);
                if (colored==9) TrJr.INSTANCE.fontCyan2.draw(batch, "10.   "+ highscoreList.get(9), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 75);
                else TrJr.INSTANCE.font2.draw(batch, "10.   "+ highscoreList.get(9), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 75);
                if (settings.getNameSet()) TrJr.INSTANCE.fontCyan2.draw(batch, "Your nickname: "+settings.getUsername(), TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f - 225);
            }
        } else if (!isDone && !hasfailed){
            if (width<1080) TrJr.INSTANCE.font3.draw(batch, "Downloading...", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
            else TrJr.INSTANCE.font2.draw(batch, "Downloading...", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 375);
        }
        if (hasfailed) {
            if (width<1080) {
                TrJr.INSTANCE.font3.draw(batch, "Download failed.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 225);
                TrJr.INSTANCE.font3.draw(batch, "An error has occured.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 175);
                TrJr.INSTANCE.font3.draw(batch, "Check your internet connection.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 125);
            } else {
                TrJr.INSTANCE.font2.draw(batch, "Download failed.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 375);
                TrJr.INSTANCE.fontCyan2.draw(batch, "An error has occured.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 325);
                TrJr.INSTANCE.font2.draw(batch, "Check your internet connection.", TrJr.INSTANCE.getScrW() / 20f, TrJr.INSTANCE.getScrH() / 2f + 275);
            }
        }
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

}

