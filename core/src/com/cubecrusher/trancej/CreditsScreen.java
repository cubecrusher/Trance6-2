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

public class CreditsScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Viewport viewport;
    protected OptionsScreen optionsScreen;
    private Stage stage;
    private TextureRegion backtexturer;
    private ShapeRenderer shapeRenderer;
    private TextureRegionDrawable backtexturerd;
    private Sprite creditss;
    private ImageButton backbutton;
    private SpriteBatch batch;
    private int height = TrJr.INSTANCE.getScrH();
    private int width = TrJr.INSTANCE.getScrW();
    private Settings settings;
    int n = 0;

    public CreditsScreen(OrthographicCamera camera) {
        this.settings = new Settings();
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        this.camera.position.set(new Vector3(width / 2f, height / 2f, 0));
        this.viewport = new FitViewport(800, 400, camera);
        this.optionsScreen = new OptionsScreen(camera);
        this.batch = new SpriteBatch();
    }

    public void create(){
        //if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        Texture credits = new Texture(Gdx.files.internal("textures/creditstext.png"));
        creditss = new Sprite(credits);
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

        if (width>=1080) {
            backbutton.setPosition(0, height / 24f);
        } else {
            backbutton.setPosition(-20, height / 12f);
            backbutton.setSize(width / 4f, height / 12f);
        }

        backbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new OptionsScreen(camera));
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
            TrJr.INSTANCE.setScreen(new OptionsScreen(camera));
        }
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
        if (width>=1080) {
            creditss.setPosition(width / 2f - 234, 0.8f*height);
            creditss.draw(batch);
        } else {
            creditss.setPosition(width / 2f - 234, height - 200);
            creditss.draw(batch);
        }
        if (settings.getLanguage()==1){
            if (width < 1080) {
                TrJr.INSTANCE.rfont3.draw(batch, "TMM43:", width / 25f, height / 2f + 325);
                TrJr.INSTANCE.rfont3.draw(batch, "   Ultimate Destruction", width / 25f, height / 2f + 285);
                TrJr.INSTANCE.rfont3.draw(batch, "JohnnyGuy:", width / 25f, height / 2f + 245);
                TrJr.INSTANCE.rfont3.draw(batch, "   ArchetypeZ8's Theme", width / 25f, height / 2f + 205);
                TrJr.INSTANCE.rfont3.draw(batch, "Kid2Will:", width / 25f, height / 2f + 165);
                TrJr.INSTANCE.rfont3.draw(batch, "   Fire Aura", width / 25f, height / 2f + 125);
                TrJr.INSTANCE.rfont3.draw(batch, "DJ_Ultimus:", width / 25f, height / 2f + 85);
                TrJr.INSTANCE.rfont3.draw(batch, "   Firefly", width / 25f, height / 2f + 45);
                TrJr.INSTANCE.rfont3.draw(batch, "ParagonX9:", width / 25f, height / 2f + 5);
                TrJr.INSTANCE.rfont3.draw(batch, "   Chaoz Fantasy ", width / 25f, height / 2f - 35);
                TrJr.INSTANCE.rfont3.draw(batch, "Kevin MacLeod: ", width / 25f, height / 2f - 75);
                TrJr.INSTANCE.rfont3.draw(batch, "   Shiny Tech2", width / 25f, height / 2f - 115);
                TrJr.INSTANCE.rfont3.draw(batch, "   Harmful and Fatal", width / 25f, height / 2f - 155);
                TrJr.INSTANCE.rfont3.draw(batch, "   Severe Tyre Damage", width / 25f, height / 2f - 195);
                TrJr.INSTANCE.rfont3.draw(batch, "   Basement Floor", width / 25f, height / 2f - 235);
                //TrJr.INSTANCE.font3.draw(batch, "Per CC-BY-SA", width / 5f, 80);
                //TrJr.INSTANCE.font3.draw(batch, "or usage permission.", width / 5f, 40);

                TrJr.INSTANCE.fontCyan3.draw(batch, "$", 15, height - 14);
                TrJr.INSTANCE.font3.draw(batch, ""+settings.getMoney(), 35, height - 14);
            } else {
                TrJr.INSTANCE.rfontCyan.draw(batch, "Музыка", width / 25f, height / 2f + 450);
                TrJr.INSTANCE.rfont2.draw(batch, "TMM43 - Ultimate Destruction", width / 25f, height / 2f + 325);
                TrJr.INSTANCE.rfont2.draw(batch, "JohnnyGuy - ArchetypeZ8's Theme", width / 25f, height / 2f + 275);
                TrJr.INSTANCE.rfont2.draw(batch, "Kid2Will - Fire Aura", width / 25f, height / 2f + 225);
                TrJr.INSTANCE.rfont2.draw(batch, "DJ_Ultimus - Firefly [Plasma RMX]", width / 25f, height / 2f + 175);
                TrJr.INSTANCE.rfont2.draw(batch, "ParagonX9 - Chaoz Fantasy (Beta)", width / 25f, height / 2f + 125);
                TrJr.INSTANCE.rfont2.draw(batch, "Kevin MacLeod: ", width / 25f, height / 2f + 25);
                TrJr.INSTANCE.rfont2.draw(batch, "   Shiny Tech2", width / 25f, height / 2f - 25);
                TrJr.INSTANCE.rfont2.draw(batch, "   Harmful and Fatal", width / 25f, height / 2f - 75);
                TrJr.INSTANCE.rfont2.draw(batch, "   Severe Tyre Damage", width / 25f, height / 2f - 125);
                TrJr.INSTANCE.rfont2.draw(batch, "   Basement Floor", width / 25f, height / 2f - 175);
                TrJr.INSTANCE.rfont2.draw(batch, "Музыка в игре лицензирована по", width / 10f, height / 5f);
                TrJr.INSTANCE.rfont2.draw(batch, "CC-BY-SA или по разрешению автора.", width / 17f, height / 5f - 50);

                TrJr.INSTANCE.fontCyan2.draw(batch, "$ ", 20, height - 28);
                TrJr.INSTANCE.font2.draw(batch, "" + settings.getMoney(), 55, height - 28);
            }
        } else {
            if (width < 1080) {
                TrJr.INSTANCE.font3.draw(batch, "By TMM43:", width / 25f, height / 2f + 325);
                TrJr.INSTANCE.font3.draw(batch, "   Ultimate Destruction", width / 25f, height / 2f + 285);
                TrJr.INSTANCE.font3.draw(batch, "By JohnnyGuy:", width / 25f, height / 2f + 245);
                TrJr.INSTANCE.font3.draw(batch, "   ArchetypeZ8's Theme", width / 25f, height / 2f + 205);
                TrJr.INSTANCE.font3.draw(batch, "By Kid2Will:", width / 25f, height / 2f + 165);
                TrJr.INSTANCE.font3.draw(batch, "   Fire Aura", width / 25f, height / 2f + 125);
                TrJr.INSTANCE.font3.draw(batch, "By DJ_Ultimus:", width / 25f, height / 2f + 85);
                TrJr.INSTANCE.font3.draw(batch, "   Firefly", width / 25f, height / 2f + 45);
                TrJr.INSTANCE.font3.draw(batch, "By ParagonX9:", width / 25f, height / 2f + 5);
                TrJr.INSTANCE.font3.draw(batch, "   Chaoz Fantasy ", width / 25f, height / 2f - 35);
                TrJr.INSTANCE.font3.draw(batch, "By Kevin MacLeod: ", width / 25f, height / 2f - 75);
                TrJr.INSTANCE.font3.draw(batch, "   Shiny Tech2", width / 25f, height / 2f - 115);
                TrJr.INSTANCE.font3.draw(batch, "   Harmful and Fatal", width / 25f, height / 2f - 155);
                TrJr.INSTANCE.font3.draw(batch, "   Severe Tyre Damage", width / 25f, height / 2f - 195);
                TrJr.INSTANCE.font3.draw(batch, "   Basement Floor", width / 25f, height / 2f - 235);
                //TrJr.INSTANCE.font3.draw(batch, "Per CC-BY-SA", width / 5f, 80);
                //TrJr.INSTANCE.font3.draw(batch, "or usage permission.", width / 5f, 40);

                TrJr.INSTANCE.fontCyan3.draw(batch, "$", 15, height - 14);
                TrJr.INSTANCE.font3.draw(batch, "" + settings.getMoney(), 35, height - 14);
            } else {
                TrJr.INSTANCE.fontCyan.draw(batch, "Music used", width / 25f, height / 2f + 450);
                TrJr.INSTANCE.font2.draw(batch, "TMM43 - Ultimate Destruction", width / 25f, height / 2f + 325);
                TrJr.INSTANCE.font2.draw(batch, "JohnnyGuy - ArchetypeZ8's Theme", width / 25f, height / 2f + 275);
                TrJr.INSTANCE.font2.draw(batch, "Kid2Will - Fire Aura", width / 25f, height / 2f + 225);
                TrJr.INSTANCE.font2.draw(batch, "DJ_Ultimus - Firefly [Plasma RMX]", width / 25f, height / 2f + 175);
                TrJr.INSTANCE.font2.draw(batch, "ParagonX9 - Chaoz Fantasy (Beta)", width / 25f, height / 2f + 125);
                TrJr.INSTANCE.font2.draw(batch, "By Kevin MacLeod: ", width / 25f, height / 2f + 25);
                TrJr.INSTANCE.font2.draw(batch, "   Shiny Tech2", width / 25f, height / 2f - 25);
                TrJr.INSTANCE.font2.draw(batch, "   Harmful and Fatal", width / 25f, height / 2f - 75);
                TrJr.INSTANCE.font2.draw(batch, "   Severe Tyre Damage", width / 25f, height / 2f - 125);
                TrJr.INSTANCE.font2.draw(batch, "   Basement Floor", width / 25f, height / 2f - 175);
                TrJr.INSTANCE.font2.draw(batch, "Music used is either licensed under", width / 19f, height / 5f);
                TrJr.INSTANCE.font2.draw(batch, "CC-BY-SA or granted use by its OC.", width / 17f, height / 5f - 50);

                TrJr.INSTANCE.fontCyan2.draw(batch, "$ ", 20, height - 28);
                TrJr.INSTANCE.font2.draw(batch, "" + settings.getMoney(), 55, height - 28);
            }
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

