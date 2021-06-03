package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Locale;

public class MainScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Stage stage;
    private TextureRegion playtexturer, opttexturer, statstexturer, exittexturer, difficultytr;
    private TextureRegionDrawable playtexturerd, opttexturerd, statstexturerd, exittexturerd, difficultytrd;
    private ImageButton playbutton, optbutton, statsbutton, exitbutton, difficultybutton;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite gamelogos;
    private String rusdifftext;
    private boolean nameset, isScoreSent, sendFailed, iseDone, isnDone, ishDone, iscDone;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private Settings settings;
    int n=0;

    public MainScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.batch = new SpriteBatch();
        this.nameset = settings.getNameSet();
        this.isScoreSent = false;
        this.sendFailed = false;
        this.iseDone = false;
        this.isnDone = false;
        this.ishDone = false;
        this.iscDone = false;
        if (settings.getDifficulty().equals("Beginner")) rusdifftext = "Новичок";
        if (settings.getDifficulty().equals("Medium")) rusdifftext = "Нормальная";
        if (settings.getDifficulty().equals("Expert")) rusdifftext = "Эксперт";
        if (settings.getDifficulty().equals("Cursed")) rusdifftext = "Хардкор";
    }

    public void create(){
        if (!settings.getLangSet()) {
            if ((Locale.getDefault().getLanguage()).equals("ru")) settings.setLanguage(1);
            else settings.setLanguage(0);
            settings.setLangSet(true);
        }

            Texture gamelogo = new Texture(Gdx.files.internal("textures/gamelogo.png"));
            gamelogos = new Sprite(gamelogo);
            Texture playtexture = new Texture(Gdx.files.internal("textures/new/play.png"));
            Texture opttexture = new Texture(Gdx.files.internal("textures/new/options.png"));
            Texture rplaytexture = new Texture(Gdx.files.internal("textures/new/rplay.png"));
            Texture rdifficultytexture = new Texture(Gdx.files.internal("textures/new/rdifficulty.png"));
            Texture scoretexture = new Texture(Gdx.files.internal("textures/new/scores.png"));
            Texture exittexture = new Texture(Gdx.files.internal("textures/new/exit.png"));
            Texture difficultytexture = new Texture(Gdx.files.internal("textures/new/difficulty.png"));

            if (settings.getLanguage()==1) {
                playtexturer = new TextureRegion(rplaytexture);
                difficultytr = new TextureRegion(rdifficultytexture);
            } else {
                playtexturer = new TextureRegion(playtexture);
                difficultytr = new TextureRegion(difficultytexture);
            }
            opttexturer = new TextureRegion(opttexture);
            statstexturer = new TextureRegion(scoretexture);
            exittexturer = new TextureRegion(exittexture);

            playtexturerd = new TextureRegionDrawable(playtexturer);
            difficultytrd = new TextureRegionDrawable(difficultytr);
            opttexturerd = new TextureRegionDrawable(opttexturer);
            statstexturerd = new TextureRegionDrawable(statstexturer);
            exittexturerd = new TextureRegionDrawable(exittexturer);

            playbutton = new ImageButton(playtexturerd);
            difficultybutton = new ImageButton(difficultytrd);
            optbutton = new ImageButton(opttexturerd);
            statsbutton = new ImageButton(statstexturerd);
            exitbutton = new ImageButton(exittexturerd);

            stage = new Stage(new ScreenViewport());

            stage.addActor(playbutton);
            stage.addActor(difficultybutton);
            stage.addActor(optbutton);
            stage.addActor(statsbutton);
            stage.addActor(exitbutton);
            Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show(){
        if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
            create();

            playbutton.setPosition(-5, height / 2f - 100);
            playbutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);

            difficultybutton.setPosition(-5, height / 2f - 275);
            difficultybutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);

            if (TrJr.INSTANCE.getScrW()>=1080) {
                difficultybutton.setPosition(-5, height / 2f - 275);
                difficultybutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);

                optbutton.setPosition(0, TrJr.INSTANCE.getScrH() / 24f);

                statsbutton.setPosition(-20, TrJr.INSTANCE.getScrH()/24f+250);
                statsbutton.setSize(TrJr.INSTANCE.getScrW()/4f,TrJr.INSTANCE.getScrH()/12f);

                exitbutton.setPosition(width - 250, TrJr.INSTANCE.getScrH()/24f);
            }
            else {
                difficultybutton.setPosition(-5, height / 2f - 200);
                difficultybutton.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);

                optbutton.setPosition(-20, TrJr.INSTANCE.getScrH() / 12f);
                optbutton.setSize(TrJr.INSTANCE.getScrW() / 4f, TrJr.INSTANCE.getScrH() / 12f);

                statsbutton.setPosition(-20, TrJr.INSTANCE.getScrH() / 12f * 2.5f);
                statsbutton.setSize(TrJr.INSTANCE.getScrW() / 4f, TrJr.INSTANCE.getScrH() / 12f);

                exitbutton.setPosition(width - TrJr.INSTANCE.getScrW()/4f+20, TrJr.INSTANCE.getScrH()/12f);
                exitbutton.setSize(TrJr.INSTANCE.getScrW()/4f,TrJr.INSTANCE.getScrH()/12f);
            }
            playbutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                    Gdx.input.setInputProcessor(null);
                    if (!nameset) TrJr.INSTANCE.setScreen(new NameScreen(camera));
                    else TrJr.INSTANCE.setScreen(new GameScreen(camera));
                }
            });
            difficultybutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                    Gdx.input.setInputProcessor(null);
                    TrJr.INSTANCE.setScreen(new DifficultyScreen(camera));
                }
            });


            optbutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                    Gdx.input.setInputProcessor(null);
                    TrJr.INSTANCE.setScreen(new OptionsScreen(camera));
                }
            });

            statsbutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                    Gdx.input.setInputProcessor(null);
                    TrJr.INSTANCE.setScreen(new StatsScreen(camera));
                }
            });

            exitbutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Gdx.app.exit();
                    System.exit(0);
                }
            });

    }

    public void update(){
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
    }

    public void submitEasyScore(){
        if (!settings.getScoreSent()){
            int bestScore = (int) (settings.geteHighScore()*100);
            String urlReqString = "http://dreamlo.com/lb/BN0B0ZjSlk2snBWFvcTOQgwXJdz69dhk2pQRiN4-CquQ/add/" + settings.getUsername() + "/" + bestScore + "/";
            HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
            Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlReqString).build();
            httpRequest.setTimeOut(0);
            Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    System.out.println("(!!!) NETWORK (EASY) RESPONSE: ");
                    settings.setScoreSent(true);
                    isScoreSent = true;
                }

                @Override
                public void failed(Throwable t) {
                    System.out.println("(!!!) submitEasyScore() FAILED: ");
                    t.printStackTrace();
                    sendFailed = true;
                }

                @Override
                public void cancelled() {
                    System.out.println("(!!!) submitEasyScore() CANCELED.");
                    sendFailed = true;
                }
            });
        }
        iseDone = true;
    }

    public void submitMediumScore(){
        if (!settings.getScoreSent()){
            int bestScore = (int) (settings.getnHighScore()*100);
            String urlReqString = "http://dreamlo.com/lb/RgmW1USbOUGLxputvY42UgxmTCP95THkW4TfGUvJItLw/add/" + settings.getUsername() + "/" + bestScore + "/";
            HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
            Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlReqString).build();
            httpRequest.setTimeOut(0);
            Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    System.out.println("(!!!) NETWORK (MEDIUM) RESPONSE: ");
                    settings.setScoreSent(true);
                    isScoreSent = true;
                }

                @Override
                public void failed(Throwable t) {
                    System.out.println("(!!!) submitMediumScore() FAILED: ");
                    t.printStackTrace();
                    sendFailed = true;
                }

                @Override
                public void cancelled() {
                    System.out.println("(!!!) submitMediumScore() CANCELED.");
                    sendFailed = true;
                }
            });
        }
        isnDone = true;
    }

    public void submitHardScore(){
        if (!settings.getScoreSent()){
            int bestScore = (int) (settings.gethHighScore()*100);
            String urlReqString = "http://dreamlo.com/lb/QJNYhELT6kum8gnBlxvuNALo_R2Fa2UUClZd3A5E0N1Q/add/" + settings.getUsername() + "/" + bestScore + "/";
            HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
            Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlReqString).build();
            httpRequest.setTimeOut(0);
            Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    System.out.println("(!!!) NETWORK (HARD) RESPONSE: ");
                    settings.setScoreSent(true);
                    isScoreSent = true;
                }

                @Override
                public void failed(Throwable t) {
                    System.out.println("(!!!) submitHardScore() FAILED: ");
                    t.printStackTrace();
                    sendFailed = true;
                }

                @Override
                public void cancelled() {
                    System.out.println("(!!!) submitHardScore() CANCELED.");
                    sendFailed = true;
                }
            });
        }
        ishDone = true;
    }

    public void submitCursedScore(){
        if (!settings.getScoreSent()){
            int bestScore = (int) (settings.getcHighScore()*100);
            String urlReqString = "http://dreamlo.com/lb/5JdylXUUXky8NJN8X6O8iwncyP4oBIQE25bWj-CYrFvQ/add/" + settings.getUsername() + "/" + bestScore + "/";
            HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
            Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlReqString).build();
            httpRequest.setTimeOut(0);
            Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    System.out.println("(!!!) NETWORK (CURSED) RESPONSE: ");
                    settings.setScoreSent(true);
                    isScoreSent = true;
                }

                @Override
                public void failed(Throwable t) {
                    System.out.println("(!!!) submitCursedScore() FAILED: ");
                    t.printStackTrace();
                    sendFailed = true;
                }

                @Override
                public void cancelled() {
                    System.out.println("(!!!) submitCursedScore() CANCELED.");
                    sendFailed = true;
                }
            });
        }
        iscDone = true;
    }

    @Override
    public void render(float delta){
        update();
        if (settings.getNameSet()) {
            if (!iseDone) submitEasyScore();
            if (!isnDone) submitMediumScore();
            if (!ishDone) submitHardScore();
            if (!iscDone) submitCursedScore();
        }
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
        if (TrJr.INSTANCE.getScrW()<1080) {
            TrJr.INSTANCE.fontCyan3.draw(batch, "$", 15, height - 14);
            TrJr.INSTANCE.font3.draw(batch, ""+settings.getMoney(), 35, height - 14);
            gamelogos.setPosition(TrJr.INSTANCE.getScrW()/2f-282, TrJr.INSTANCE.getScrH()/6f*3.5f);
            if (!settings.getScoreSent() && settings.getLaunch()) {
                if (settings.getLanguage()==1){
                    if (!sendFailed)
                        TrJr.INSTANCE.rfontCyan3.draw(batch, "Отправляем результаты...", 20, height / 2f + 125);
                    else
                        TrJr.INSTANCE.rfontCyan3.draw(batch, "Произошла ошибка. Попробуйте позже.", 20, height / 2f + 125);
                } else {
                    if (!sendFailed)
                        TrJr.INSTANCE.fontCyan3.draw(batch, "Sending your scores...", 20, height / 2f + 125);
                    else
                        TrJr.INSTANCE.fontCyan3.draw(batch, "Failed to send scores. Retry later.", 20, height / 2f + 125);
                }
            }
            if (settings.getLanguage()==1) TrJr.INSTANCE.rfont3.draw(batch, "Сложность: " + rusdifftext, 20, height / 2f + 35);
            else TrJr.INSTANCE.font3.draw(batch, "Difficulty: " + settings.getDifficulty(), 20, height / 2f + 35);

            TrJr.INSTANCE.font3.draw(batch, "1.1.1a", 20, 40);
        }
        else {
            TrJr.INSTANCE.fontCyan2.draw(batch, "$ ", 20, height - 28);
            TrJr.INSTANCE.font2.draw(batch, ""+settings.getMoney(), 55, height - 28);
            if (!settings.getScoreSent()){
                if (settings.getLanguage()==1) {
                    if (!sendFailed)
                        TrJr.INSTANCE.rfontCyan2.draw(batch, "Отправляем результаты...", 25, height / 2f + 175);
                    else
                        TrJr.INSTANCE.rfontCyan2.draw(batch, "Произошла ошибка. Попробуйте позже.", 25, height / 2f + 175);
                } else {
                    if (!sendFailed)
                        TrJr.INSTANCE.fontCyan2.draw(batch, "Sending your scores...", 25, height / 2f + 175);
                    else
                        TrJr.INSTANCE.fontCyan2.draw(batch, "Failed to send scores. Retry later.", 25, height / 2f + 175);
                }
            }
            gamelogos.setPosition(TrJr.INSTANCE.getScrW()/2f-282, TrJr.INSTANCE.getScrH()-512);
            if (settings.getLanguage()==1) TrJr.INSTANCE.rfont2.draw(batch, "Сложность: " + rusdifftext, 25, height / 2f + 125);
            else TrJr.INSTANCE.font2.draw(batch, "Difficulty: " + settings.getDifficulty(), 25, height / 2f + 125);
            TrJr.INSTANCE.font2.draw(batch, "1.1.1a", 20, 40);
        }
        gamelogos.draw(batch);
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
