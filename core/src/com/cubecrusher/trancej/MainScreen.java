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
    private TextureRegion playtexturer, opttexturer, statstexturer, exittexturer, difficultytr, shoptr, miletr;
    private TextureRegionDrawable playtexturerd, opttexturerd, statstexturerd, exittexturerd, difficultytrd, shoptrd, miletrd;
    private ImageButton playbutton, optbutton, statsbutton, exitbutton, difficultybutton, shopbutton, milebutton;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite gamelogos;
    private String rusdifftext;
    private boolean nameset, isScoreSent, sendFailed, iseDone, isnDone, ishDone, iscDone;
    private int height = TrJr.INSTANCE.getScrH();
    private int width = TrJr.INSTANCE.getScrW();
    private Settings settings;
    int n = 0;


    // Главный экран. Как бы отправная точка. Отсюда можно попасть куда угодно, откуда угодно [источник?]


    public MainScreen(OrthographicCamera camera) {
        this.settings = new Settings();
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        this.camera.position.set(new Vector3(width / 2f, height / 2f, 0));
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
            Texture shoptexture = new Texture(Gdx.files.internal("textures/new/shop.png"));
            Texture actexture = new Texture(Gdx.files.internal("textures/new/milestone.png"));
            Texture rplaytexture = new Texture(Gdx.files.internal("textures/new/rplay.png"));
            Texture rdifficultytexture = new Texture(Gdx.files.internal("textures/new/rdifficulty.png"));
            Texture scoretexture = new Texture(Gdx.files.internal("textures/new/stats.png"));
            Texture exittexture = new Texture(Gdx.files.internal("textures/new/exit.png"));
            Texture difficultytexture = new Texture(Gdx.files.internal("textures/new/difficulty.png"));
            Texture rshoptexture = new Texture(Gdx.files.internal("textures/new/rshop.png"));

            if (settings.getLanguage()==1) {
                playtexturer = new TextureRegion(rplaytexture);
                difficultytr = new TextureRegion(rdifficultytexture);
                shoptr = new TextureRegion(rshoptexture);
            } else {
                playtexturer = new TextureRegion(playtexture);
                difficultytr = new TextureRegion(difficultytexture);
                shoptr = new TextureRegion(shoptexture);
            }
            opttexturer = new TextureRegion(opttexture);
            statstexturer = new TextureRegion(scoretexture);
            exittexturer = new TextureRegion(exittexture);
            miletr = new TextureRegion(actexture);
            shoptr = new TextureRegion(shoptr);

            playtexturerd = new TextureRegionDrawable(playtexturer);
            difficultytrd = new TextureRegionDrawable(difficultytr);
            opttexturerd = new TextureRegionDrawable(opttexturer);
            statstexturerd = new TextureRegionDrawable(statstexturer);
            exittexturerd = new TextureRegionDrawable(exittexturer);
            miletrd = new TextureRegionDrawable(miletr);
            shoptrd = new TextureRegionDrawable(shoptr);

            playbutton = new ImageButton(playtexturerd);
            difficultybutton = new ImageButton(difficultytrd);
            optbutton = new ImageButton(opttexturerd);
            statsbutton = new ImageButton(statstexturerd);
            exitbutton = new ImageButton(exittexturerd);
            milebutton = new ImageButton(miletrd);
            shopbutton = new ImageButton(shoptrd);

            stage = new Stage(new ScreenViewport());

            stage.addActor(playbutton);
            stage.addActor(difficultybutton);
            stage.addActor(optbutton);
            stage.addActor(statsbutton);
            stage.addActor(exitbutton);
            stage.addActor(milebutton);
            stage.addActor(shopbutton);
            Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        create();
        if (width >= 1080) {

            // Это *феноменально* глупо. Но необходимо, ибо Viewport'ы не сработали.

            playbutton.setPosition(-5, height / 2f);
            playbutton.setSize(width - 80, height / 12f);

            shopbutton.setPosition(-5, height / 2f - 350);
            shopbutton.setSize(width - 80, height / 12f);

            difficultybutton.setPosition(0, height / 2f - 175);
            difficultybutton.setSize(width - 80, height / 12f);

            optbutton.setPosition(0, height / 24f);

            statsbutton.setPosition(-10, height / 24f + 150);
            statsbutton.setSize(width / 4f, height / 12f);

            milebutton.setPosition(-10, height / 24f + 325);
            milebutton.setSize(width / 4f, height / 12f);

                exitbutton.setPosition(width - 250, height/24f);
            }
            else {
            // Здесь так же. Да и *везде* так же. Адаптацию интерфейса - никак не успею.
            playbutton.setPosition(-5, height / 2f + 20);
            playbutton.setSize(width - 80, height / 12f);

            difficultybutton.setPosition(-5, height / 2f - 80);
            difficultybutton.setSize(width - 80, height / 12f);

            shopbutton.setPosition(-5, height / 2f - 180);
            shopbutton.setSize(width - 80, height / 12f);

            optbutton.setPosition(0, height / 12f);
            optbutton.setSize(width / 4f, height / 12f);

            statsbutton.setPosition(0, height / 12f * 2.1f);
            statsbutton.setSize(width / 4f, height / 12f);

            milebutton.setPosition(0, height / 12f * 3.2f);
            milebutton.setSize(width / 4f, height / 12f);

            exitbutton.setPosition(width - width / 4f + 20, height / 12f);
            exitbutton.setSize(width / 4f, height / 12f);
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

            shopbutton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                    Gdx.input.setInputProcessor(null);
                    TrJr.INSTANCE.setScreen(new ShopScreen(camera));
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

            milebutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                    Gdx.input.setInputProcessor(null);
                    TrJr.INSTANCE.setScreen(new AcScreen(camera));
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

        if (n <= 2 && settings.getEpilepsy()) {
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(0, 0, width, height);
            shapeRenderer.end();
            n++;
        }
        batch.begin();
        if (width<1080) {
            TrJr.INSTANCE.fontCyan3.draw(batch, "$", 15, height - 14);
            TrJr.INSTANCE.font3.draw(batch, "" + settings.getMoney(), 35, height - 14);
            gamelogos.setPosition(width / 2f - 282, height / 6f * 3.8f);
            if (!settings.getScoreSent() && settings.getLaunch()) {
                if (settings.getLanguage()==1){
                    if (!sendFailed)
                        TrJr.INSTANCE.rfontCyan3.draw(batch, "Отправляем результаты...", 20, height / 2f + 150);
                    else
                        TrJr.INSTANCE.rfontCyan3.draw(batch, "Произошла ошибка. Попробуйте позже.", 20, height / 2f + 150);
                } else {
                    if (!sendFailed)
                        TrJr.INSTANCE.fontCyan3.draw(batch, "Sending your scores...", 20, height / 2f + 150);
                    else
                        TrJr.INSTANCE.fontCyan3.draw(batch, "Failed to send scores. Retry later.", 20, height / 2f + 150);
                }
            }
            if (settings.getLanguage() == 1)
                TrJr.INSTANCE.rfont3.draw(batch, "Сложность: " + rusdifftext, 20, height / 2f + 170);
            else
                TrJr.INSTANCE.font3.draw(batch, "Difficulty: " + settings.getDifficulty(), 20, height / 2f + 170);

            TrJr.INSTANCE.font3.draw(batch, "1.2.0a", 20, 40);
        }
        else {
            TrJr.INSTANCE.fontCyan2.draw(batch, "$ ", 20, height - 28);
            TrJr.INSTANCE.font2.draw(batch, ""+settings.getMoney(), 55, height - 28);
            if (!settings.getScoreSent()){
                if (settings.getLanguage()==1) {
                    if (!sendFailed)
                        TrJr.INSTANCE.rfontCyan2.draw(batch, "Отправляем результаты...", 25, height / 2f + 275);
                    else
                        TrJr.INSTANCE.rfontCyan2.draw(batch, "Произошла ошибка. Попробуйте позже.", 25, height / 2f + 275);
                } else {
                    if (!sendFailed)
                        TrJr.INSTANCE.fontCyan2.draw(batch, "Sending your scores...", 25, height / 2f + 275);
                    else
                        TrJr.INSTANCE.fontCyan2.draw(batch, "Failed to send scores. Retry later.", 25, height / 2f + 275);
                }
            }
            gamelogos.setPosition(width/2f-282, height-512);
            if (settings.getLanguage()==1) TrJr.INSTANCE.rfont2.draw(batch, "Сложность: " + rusdifftext, 25, height / 2f + 225);
            else TrJr.INSTANCE.font2.draw(batch, "Difficulty: " + settings.getDifficulty(), 25, height / 2f + 225);
            TrJr.INSTANCE.font2.draw(batch, "1.2.0a", 20, 40);
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
