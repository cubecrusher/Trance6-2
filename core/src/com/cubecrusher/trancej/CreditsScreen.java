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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CreditsScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    protected OptionsScreen optionsScreen;
    private Stage stage;
    private TextureRegion backtexturer;
    private ShapeRenderer shapeRenderer;
    private TextureRegionDrawable backtexturerd;
    private Sprite creditss;
    private ImageButton backbutton;
    private SpriteBatch batch;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private Settings settings;
    int n=0;


    public CreditsScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
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

        if (TrJr.INSTANCE.getScrW()>=1080) {
            backbutton.setPosition(0, TrJr.INSTANCE.getScrH() / 24f);
        } else {
            backbutton.setPosition(-20, TrJr.INSTANCE.getScrH() / 12f);
            backbutton.setSize(TrJr.INSTANCE.getScrW() / 4f, TrJr.INSTANCE.getScrH() / 12f);
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
        if (n<=2) {
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(0, 0, width, height);
            shapeRenderer.end();
            n++;
        }
        batch.begin();
        if (TrJr.INSTANCE.getScrW()>=1080) {
            creditss.setPosition(width / 2f - 234, 0.8f*height);
            creditss.draw(batch);
        } else {
            creditss.setPosition(width / 2f - 234, height - 200);
            creditss.draw(batch);
        }

        if (TrJr.INSTANCE.getScrW() < 1080) {
            TrJr.INSTANCE.font3.draw(batch, "By TMM43:", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 325);
            TrJr.INSTANCE.font3.draw(batch, "   Ultimate Destruction", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 285);
            TrJr.INSTANCE.font3.draw(batch, "By JohnnyGuy:", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 245);
            TrJr.INSTANCE.font3.draw(batch, "   ArchetypeZ8's Theme", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 205);
            TrJr.INSTANCE.font3.draw(batch, "By Kid2Will:", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 165);
            TrJr.INSTANCE.font3.draw(batch, "   Fire Aura", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 125);
            TrJr.INSTANCE.font3.draw(batch, "By DJ_Ultimus:", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 85);
            TrJr.INSTANCE.font3.draw(batch, "   Firefly", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 45);
            TrJr.INSTANCE.font3.draw(batch, "By ParagonX9:", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 5);
            TrJr.INSTANCE.font3.draw(batch, "   Chaoz Fantasy ", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f - 35);
            TrJr.INSTANCE.font3.draw(batch, "By Kevin MacLeod: ", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f - 75);
            TrJr.INSTANCE.font3.draw(batch, "   Shiny Tech2", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f - 115);
            TrJr.INSTANCE.font3.draw(batch, "   Harmful and Fatal", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f - 155);
            TrJr.INSTANCE.font3.draw(batch, "   Severe Tyre Damage", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f - 195);
            TrJr.INSTANCE.font3.draw(batch, "   Basement Floor", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f - 235);
            //TrJr.INSTANCE.font3.draw(batch, "Per CC-BY-SA", TrJr.INSTANCE.getScrW() / 5f, 80);
            //TrJr.INSTANCE.font3.draw(batch, "or usage permission.", TrJr.INSTANCE.getScrW() / 5f, 40);
        } else {
            TrJr.INSTANCE.fontCyan.draw(batch, "Music used", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 450);
            TrJr.INSTANCE.font2.draw(batch, "TMM43 - Ultimate Destruction", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 325);
            TrJr.INSTANCE.font2.draw(batch, "JohnnyGuy - ArchetypeZ8's Theme", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 275);
            TrJr.INSTANCE.font2.draw(batch, "Kid2Will - Fire Aura", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 225);
            TrJr.INSTANCE.font2.draw(batch, "DJ_Ultimus - Firefly [Plasma RMX]", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 175);
            TrJr.INSTANCE.font2.draw(batch, "ParagonX9 - Chaoz Fantasy (Beta)", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 125);
            TrJr.INSTANCE.font2.draw(batch, "By Kevin MacLeod: ", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f + 25);
            TrJr.INSTANCE.font2.draw(batch, "   Shiny Tech2", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f - 25);
            TrJr.INSTANCE.font2.draw(batch, "   Harmful and Fatal", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f - 75);
            TrJr.INSTANCE.font2.draw(batch, "   Severe Tyre Damage", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f - 125);
            TrJr.INSTANCE.font2.draw(batch, "   Basement Floor", TrJr.INSTANCE.getScrW() / 25f, TrJr.INSTANCE.getScrH() / 2f - 175);
            TrJr.INSTANCE.font2.draw(batch, "Music used is either licensed under", TrJr.INSTANCE.getScrW() / 19f, TrJr.INSTANCE.getScrH()/5f);
            TrJr.INSTANCE.font2.draw(batch, "CC-BY-SA or granted use by its OC.", TrJr.INSTANCE.getScrW() / 17f, TrJr.INSTANCE.getScrH()/5f-50);
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

