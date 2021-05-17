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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class NameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Viewport viewport;
    protected GameScreen gameScreen;
    private Stage stage;
    private Sprite usernames, textbox;
    private TextureRegion playtexturer;
    private TextureRegionDrawable playtexturerd;
    private ImageButton playbutton;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private TextInputListener listener = new TextInputListener();
    private Settings settings;
    private TextField textField;
    private TextField.TextFieldStyle textFieldStyle;
    int n=0;
    boolean a=true, existing=false, isDone=false, hasfailed=false;
    public ArrayList<String> highscoreList;
    private String currentUsername, matchingUsername;


    public NameScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.camera.position.set(new Vector3(width/2f, height/2f,0));
        this.viewport = new FitViewport(800,400, camera);
        this.gameScreen = new GameScreen(camera);
        this.shapeRenderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.textFieldStyle = new TextField.TextFieldStyle(TrJr.INSTANCE.fontCyan, Color.WHITE,null, null,null);
        this.textField = new TextField("Nickname", textFieldStyle);
        matchingUsername = "Nickname";
        this.highscoreList = new ArrayList<>();
    }

    public void create(){
        //if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        Texture nickname = new Texture(Gdx.files.internal("textures/nickname.png"));
        usernames = new Sprite(nickname);
        Texture playtexture = new Texture(Gdx.files.internal("textures/new/next.png"));
        Texture texttexture = new Texture(Gdx.files.internal("textures/new/text.png"));
        textbox = new Sprite(texttexture);

        playtexturer = new TextureRegion(playtexture);
        playtexturerd = new TextureRegionDrawable(playtexturer);
        playbutton = new ImageButton(playtexturerd);

        textField.setX(40);
        textField.setY(height / 2f - 200);
        textField.setWidth(width-80);
        textField.setHeight(height/12f);
        textField.setText("-");

        stage = new Stage(new ScreenViewport());
        stage.addActor(textField);
        stage.addActor(playbutton);
        Gdx.input.setInputProcessor(stage);

    }

    public void getEasyScores() {
        String urlString = "http://dreamlo.com/lb/BN0B0ZjSlk2snBWFvcTOQgwXJdz69dhk2pQRiN4-CquQ/json/";
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlString).build();
        httpRequest.setTimeOut(0);
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
                    for (int i = 0; i < entries.size-1; i++) {
                        JsonValue score = entries.get(i);
                        String name = score.getString("name");
                        existing = name.equals(settings.getUsername());
                        highscoreList.add(name);
                    }
                    isDone = true;
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

    public void getNormalScores() {
        String urlString = "http://dreamlo.com/lb/RgmW1USbOUGLxputvY42UgxmTCP95THkW4TfGUvJItLw/json/";
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlString).build();
        httpRequest.setTimeOut(0);
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
                    for (int i = 0; i < entries.size-1; i++) {
                        JsonValue score = entries.get(i);
                        String name = score.getString("name");
                        existing = name.equals(settings.getUsername());
                        highscoreList.add(name);
                    }
                    isDone = true;
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

    public void getHardScores() {
        String urlString = "http://dreamlo.com/lb/QJNYhELT6kum8gnBlxvuNALo_R2Fa2UUClZd3A5E0N1Q/json/";
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlString).build();
        httpRequest.setTimeOut(0);
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
                    for (int i = 0; i < entries.size-1; i++) {
                        JsonValue score = entries.get(i);
                        String name = score.getString("name");
                        existing = name.equals(settings.getUsername());
                        highscoreList.add(name);
                    }
                    isDone = true;
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

    public void getCursedScores() {
        String urlString = "http://dreamlo.com/lb/5JdylXUUXky8NJN8X6O8iwncyP4oBIQE25bWj-CYrFvQ/json/";
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlString).build();
        httpRequest.setTimeOut(0);
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
                    for (int i = 0; i < entries.size-1; i++) {
                        JsonValue score = entries.get(i);
                        String name = score.getString("name");
                        existing = name.equals(settings.getUsername());
                        highscoreList.add(name);
                    }
                    isDone = true;
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
    public void show(){
        create();
        textbox.setPosition(-20, height / 2f - 200);
        textbox.setSize(width-80,TrJr.INSTANCE.getScrH()/12f);
        if (TrJr.INSTANCE.getScrW()>=1080) {
            playbutton.setPosition(width - 250, height/24f);
        }
        else {
            playbutton.setPosition(width - width/4f+20, height/12f);
            playbutton.setSize(width/4f,height/12f);
        }


        playbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!existing && !currentUsername.matches("") && !currentUsername.matches("-") && !currentUsername.matches("Nickname") && currentUsername.length()<=12 && currentUsername.length()>=3 && !currentUsername.contains("(") && !currentUsername.contains(")") && !currentUsername.contains("*")) {
                    if (settings.isSoundOn()) {
                        Assets.playSound(Assets.blip1);
                    }
                    Gdx.input.setInputProcessor(null);
                    if (!hasfailed) settings.setNameSet(true);
                    if (settings.getLaunch()) TrJr.INSTANCE.setScreen(new TutorialScreen(camera));
                    else TrJr.INSTANCE.setScreen(new GameScreen(camera));
                }
            }
        });

        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                if (currentUsername.length()<=8) currentUsername=textField.getText().trim();
                if (currentUsername.indexOf('*') != -1 || currentUsername.indexOf(')') != -1 || currentUsername.indexOf('(') != -1){
                    currentUsername = "";
                    textField.setText(currentUsername);
                }
            }
        });

    }

    public void update(){
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
    }

    @Override
    public void render(float delta) {
        if (a) {
            getEasyScores();
            getNormalScores();
            getHardScores();
            getCursedScores();
            a=false;
        }
        currentUsername = textField.getText();
        if (isDone && !existing) {
                for (int i = 0; i < highscoreList.size(); i++) {
                    if (currentUsername.matches(highscoreList.get(i))) {
                        matchingUsername = currentUsername;
                        existing = true;
                        break;
                    }
            }
        }
        if (isDone) if (!matchingUsername.equals(currentUsername)) existing=false;
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
        if (TrJr.INSTANCE.getScrW()>=1080) {
            usernames.setPosition(width / 2f - 294, 0.8f*height);
        } else {
            usernames.setPosition(width / 2f - 294, height - 200);
        }
        usernames.draw(batch);
        textbox.draw(batch);

        if (TrJr.INSTANCE.getScrW() < 1080) {
            TrJr.INSTANCE.font3.draw(batch, "Think twice before playing.", width / 20f, height / 2f + 225);
            TrJr.INSTANCE.font3.draw(batch, "You can only set it once.", width / 20f, height / 2f + 175);
            TrJr.INSTANCE.font3.draw(batch, "Characters ( ) * cannot be used.", width / 20f, height / 2f + 125);

            if (!isDone) TrJr.INSTANCE.font3.draw(batch, "Getting nicknames...", width / 20f, height / 2f + 75);

            if (hasfailed && !(currentUsername.length()>12 || currentUsername.length()<3) ) {
                TrJr.INSTANCE.fontCyan3.draw(batch, "Error getting nicknames.", width / 20f, height / 2f + 75);
                TrJr.INSTANCE.fontCyan3.draw(batch, "Check your internet connection.", width / 20f, height / 2f + 25);
            }
            if (existing && !hasfailed && !(currentUsername.length()>12 || currentUsername.length()<3)) {
                TrJr.INSTANCE.fontCyan3.draw(batch, "Someone already has this nickname.", width / 20f, height / 2f + 75);
                TrJr.INSTANCE.fontCyan3.draw(batch, "Please set another one.", width / 20f, height / 2f + 25);
            }
            if ((currentUsername.length()>12 || currentUsername.length()<3) && isDone && !hasfailed) {
                TrJr.INSTANCE.fontCyan3.draw(batch, "This nickname is too long or short.", width / 20f, height / 2f + 75);
                TrJr.INSTANCE.font3.draw(batch, "It must be 3-12 characters long.", width / 20f, height / 2f + 25);
            }
        } else {
            TrJr.INSTANCE.font2.draw(batch, "Think twice before continuing.", width / 20f, height / 2f + 375);
            TrJr.INSTANCE.font2.draw(batch, "You can only set it once.", width / 20f, height / 2f + 325);
            TrJr.INSTANCE.font2.draw(batch, "Characters ( ) * cannot be used.", width / 20f, height / 2f + 275);

            if (!isDone && !hasfailed) TrJr.INSTANCE.font2.draw(batch, "Getting nicknames...", width / 20f, height / 2f + 225);

            if (hasfailed && !(currentUsername.length()>12 || currentUsername.length()<3)) {
                TrJr.INSTANCE.fontCyan2.draw(batch, "Error getting nicknames.", width / 20f, height / 2f + 225);
                TrJr.INSTANCE.fontCyan2.draw(batch, "Check your internet connection.", width / 20f, height / 2f + 175);
            }
            if (existing && !hasfailed && !(currentUsername.length()>12 || currentUsername.length()<3)) {
                TrJr.INSTANCE.fontCyan2.draw(batch, "Someone already has this nickname.", width / 20f, height / 2f + 225);
                TrJr.INSTANCE.fontCyan2.draw(batch, "Please set another one.", width / 20f, height / 2f + 175);
            }
            if ((currentUsername.length()>12 || currentUsername.length()<3) && isDone && !hasfailed) {
                TrJr.INSTANCE.fontCyan2.draw(batch, "This nickname is too long or short.", width / 20f, height / 2f + 225);
                TrJr.INSTANCE.fontCyan2.draw(batch, "It must be 3-12 characters long.", width / 20f, height / 2f + 175);
            }
        }
        if (!existing && !textField.getText().equals("")) settings.setUsername(currentUsername);
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

