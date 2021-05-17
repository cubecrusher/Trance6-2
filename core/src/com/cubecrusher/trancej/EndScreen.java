package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EndScreen extends ScreenAdapter {
    public static float time;
    public static boolean newBest = false, netNewBest = false;
    private OrthographicCamera camera;
    protected GameScreen gameScreen;
    private Stage stage;
    private TextureRegion playtexturer, backtexturer;
    private TextureRegionDrawable playtexturerd, backtexturerd;
    private Viewport viewport;
    private ImageButton playbutton, backbutton;
    private SpriteBatch batch;
    private Sprite gameovers;
    private String mockery;
    private double mockeryd = Math.random();
    private int height = TrJr.INSTANCE.getScrH();
    private int width = TrJr.INSTANCE.getScrW();
    private Settings settings;
    private int spritey = 0, spritey2 = 0;
    int a = 0;


    public EndScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.camera.position.set(new Vector3(width/2f, height/2f,0));
        this.gameScreen = new GameScreen(camera);
        this.batch = new SpriteBatch();
        this.viewport = new FitViewport(800,400, camera);
        if (mockeryd<=0.1) mockery = "(!) That's a bruh moment";
        if (mockeryd>0.1 && mockeryd<=0.2) mockery = "(!) You are not speed";
        if (mockeryd>0.2 && mockeryd<=0.3) mockery = "(!) Everyone disliked that";
        if (mockeryd>0.3 && mockeryd<=0.4) mockery = "(!) Not even close";
        if (mockeryd>0.4 && mockeryd<=0.5) mockery = "(!) This is no place to die!";
        if (mockeryd>0.5 && mockeryd<=0.6) mockery = "(!) All you got?";
        if (mockeryd>0.6 && mockeryd<=0.7) mockery = "(!) Brap SFX #4";
        if (mockeryd>0.7 && mockeryd<=0.8) mockery = "(!) #yolo";
        if (mockeryd>0.8 && mockeryd<=0.9) mockery = "(!) That's kinda small. Yikes!";
        if (mockeryd>0.9 && mockeryd<=1.0) mockery = "(!) You've been gnomed!";
    }

    public void create(){
        //if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        Texture gameover = new Texture(Gdx.files.internal("textures/gameover.png"));
        gameovers = new Sprite(gameover);
        Texture playtexture = new Texture(Gdx.files.internal("textures/new/play.png"));
        Texture backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));


        playtexturer = new TextureRegion(playtexture);
        backtexturer = new TextureRegion(backtexture);

        playtexturerd = new TextureRegionDrawable(playtexturer);
        backtexturerd = new TextureRegionDrawable(backtexturer);

        playbutton = new ImageButton(playtexturerd);
        backbutton = new ImageButton(backtexturerd);

        stage = new Stage(new ScreenViewport());
        stage.addActor(playbutton);
        stage.addActor(backbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show(){
        create();
        playbutton.setPosition(-5, height / 2f - 100);
        playbutton.setSize(width-80,height/12f);
        if (width>=1080) {
            backbutton.setPosition(0, height / 24f);
        } else {
            backbutton.setPosition(-20, height / 12f);
            backbutton.setSize(width / 4f, height / 12f);
        }

        playbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) {
                    Assets.playSound(Assets.blip1);
                    Assets.stopMusic(Assets.mainMenu);
                }
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new GameScreen(camera));
            }
        });
        backbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                TrJr.INSTANCE.setScreen(new MainScreen(camera));
            }
        });

    }

    public void submitEasyScore(){
        if (newBest) netNewBest = true;
        if (netNewBest){
            int bestScore = (int) (settings.geteHighScore()*100);
            String urlReqString = "http://dreamlo.com/lb/BN0B0ZjSlk2snBWFvcTOQgwXJdz69dhk2pQRiN4-CquQ/add/" + settings.getUsername() + "/" + bestScore + "/";
            HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
            Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlReqString).build();
            httpRequest.setTimeOut(0);
            Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    System.out.println("(!!!) NETWORK (EASY) RESPONSE: ");
                    System.out.println();
                    settings.setScoreSent(true);
                }

                @Override
                public void failed(Throwable t) {
                    System.out.println("(!!!) submitEasyScore() FAILED: ");
                    t.printStackTrace();
                }

                @Override
                public void cancelled() {

                }
            });
            netNewBest = false;
        }
    }

    public void submitNormalScore(){
        if (newBest) netNewBest = true;
        if (netNewBest){
            int bestScore = (int) (settings.getnHighScore()*100);
            String urlReqString = "http://dreamlo.com/lb/RgmW1USbOUGLxputvY42UgxmTCP95THkW4TfGUvJItLw/add/" + settings.getUsername() + "/" + bestScore + "/";
            HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
            Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlReqString).build();
            httpRequest.setTimeOut(0);
            Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    System.out.println("(!!!) NETWORK (MEDIUM) RESPONSE: ");
                    System.out.println();
                    settings.setScoreSent(true);
                }

                @Override
                public void failed(Throwable t) {
                    System.out.println("(!!!) submitMediumScore() FAILED: ");
                    t.printStackTrace();
                }

                @Override
                public void cancelled() {

                }
            });
            netNewBest = false;
        }
    }

    public void submitHardScore(){
        if (newBest) netNewBest = true;
        if (netNewBest){
            int bestScore = (int) (settings.gethHighScore()*100);
            String urlReqString = "http://dreamlo.com/lb/QJNYhELT6kum8gnBlxvuNALo_R2Fa2UUClZd3A5E0N1Q/add/" + settings.getUsername() + "/" + bestScore + "/";
            HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
            Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlReqString).build();
            httpRequest.setTimeOut(0);
            Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    System.out.println("(!!!) NETWORK (HARD) RESPONSE: ");
                    System.out.println();
                    settings.setScoreSent(true);
                }

                @Override
                public void failed(Throwable t) {
                    System.out.println("(!!!) submitHardScore() FAILED: ");
                    t.printStackTrace();
                }

                @Override
                public void cancelled() {

                }
            });
            netNewBest = false;
        }
    }

    public void submitCursedScore(){
        if (newBest) netNewBest = true;
        if (netNewBest){
            int bestScore = (int) (settings.getcHighScore()*100);
            String urlReqString = "http://dreamlo.com/lb/5JdylXUUXky8NJN8X6O8iwncyP4oBIQE25bWj-CYrFvQ/add/" + settings.getUsername() + "/" + bestScore + "/";
            HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
            Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlReqString).build();
            httpRequest.setTimeOut(0);
            Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    System.out.println("(!!!) NETWORK (CURSED) RESPONSE: ");
                    System.out.println();
                    settings.setScoreSent(true);
                }

                @Override
                public void failed(Throwable t) {
                    System.out.println("(!!!) submitCursedScore() FAILED: ");
                    t.printStackTrace();
                }

                @Override
                public void cancelled() {

                }
            });
            netNewBest = false;
        }
    }

    public void update(){
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
        if (TrJr.INSTANCE.getScrW()<1080) {
            if (spritey < height / 3.5f) {
                spritey += 25;
                spritey2 += 25;
            }
            if (spritey2 < height / 3.5f * 2) spritey2 += 25;
        } else {
            if (spritey < height / 4f) {
                spritey += 25;
                spritey2 += 25;
            }
            if (spritey2 < height / 4f * 2) spritey2 += 25;
        }
    }

    @Override
    public void render(float delta) {
        a++;
        if (a<=1) {
            submitEasyScore();
            submitNormalScore();
            submitHardScore();
            submitCursedScore();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            Gdx.input.setCatchKey(Input.Keys.BACK,true);
            TrJr.INSTANCE.setScreen(new MainScreen(camera));
        }
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        gameovers.setPosition(width / 2f - 193, height - spritey);
        gameovers.draw(batch);

        if (TrJr.INSTANCE.getScrW() < 1080) {
            if (spritey2 > TrJr.INSTANCE.getScrH() / 3.5f * 2) {
                TrJr.INSTANCE.font2.draw(batch, "Time: " + time, width / 20f, height / 2f + 75);

                stage.act(Gdx.graphics.getDeltaTime());
                stage.draw();
            }
        } else {
            if (spritey2 > height / 4f * 2) {
                TrJr.INSTANCE.font.draw(batch, "Time: " + time, width / 20f, height / 2f + 300);
                    if (!newBest) {
                        if (settings.getDifficulty().equals("Beginner")) TrJr.INSTANCE.font2.draw(batch, "Best: " + settings.geteHighScore(), width / 20f, height / 2f + 225);
                        if (settings.getDifficulty().equals("Medium")) TrJr.INSTANCE.font2.draw(batch, "Best: " + settings.getnHighScore(), width / 20f, height / 2f + 225);
                        if (settings.getDifficulty().equals("Expert")) TrJr.INSTANCE.font2.draw(batch, "Best: " + settings.gethHighScore(), width / 20f, height / 2f + 225);
                        if (settings.getDifficulty().equals("Cursed")) TrJr.INSTANCE.font2.draw(batch, "Best: " + settings.getcHighScore(), width / 20f, height / 2f + 225);
                        TrJr.INSTANCE.font2.draw(batch, mockery, width / 20f, height / 2f + 175);
                    }
                    else TrJr.INSTANCE.fontCyan2.draw(batch, "(!!!) New highscore!", width / 20f, height / 2f + 225);
                    stage.act(Gdx.graphics.getDeltaTime());
                    stage.draw();
                }
            }
        batch.end();
    }

    @Override
    public void dispose(){

    }

    @Override
    public void hide(){

    }

}
