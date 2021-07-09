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
import com.badlogic.gdx.utils.viewport.Viewport;

public class OptionsScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Stage stage;
    private Settings settings;
    Viewport viewport;
    Texture optionstextt;
    Texture soundtexture;
    Texture musictexture;
    Texture backtexture;
    Texture creditstexture;
    Texture langtexture;
    Texture epilepsytexture;
    private TextureRegion soundtexturer, musictexturer, backtexturer, creditstexturer, langtexturer, eptexturer;
    private TextureRegionDrawable soundtexturerd, musictexturerd, backtexturerd, creditstexturerd, langtexturerd, eptexturerd;
    private ImageButton soundbutton, musicbutton, backbutton, creditsbutton, langbutton, epbutton;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private Sprite optionstext;
    private int height = TrJr.INSTANCE.getScrH();
    private int width = TrJr.INSTANCE.getScrW();
    private int spritey = 0;
    int n = 0;

    // Экран настроек. Тут можно вкл/выкл звук, музыку, мерцание и сменить язык если очень хочется.
    // Текст может съезжать от кнопок, ибо ужасная адаптация - это ведь не текстурки

    public OptionsScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.camera.position.set(new Vector3(width / 2f, height / 2f, 0));
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.settings = new Settings();
    }

    public void create() {
        {
            optionstextt = new Texture(Gdx.files.internal("textures/optionstext.png"));
            optionstext = new Sprite(optionstextt);
            soundtexture = new Texture(Gdx.files.internal("textures/new/sound.png"));
            musictexture = new Texture(Gdx.files.internal("textures/new/music.png"));
            backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));
            creditstexture = new Texture(Gdx.files.internal("textures/new/credits.png"));
            langtexture = new Texture(Gdx.files.internal("textures/new/language.png"));
            epilepsytexture = new Texture(Gdx.files.internal("textures/new/epilepsy.png"));

            soundtexturer = new TextureRegion(soundtexture);
            musictexturer = new TextureRegion(musictexture);
            backtexturer = new TextureRegion(backtexture);
            creditstexturer = new TextureRegion(creditstexture);
            langtexturer = new TextureRegion(langtexture);
            eptexturer = new TextureRegion(epilepsytexture);

            soundtexturerd = new TextureRegionDrawable(soundtexturer);
            musictexturerd = new TextureRegionDrawable(musictexturer);
            backtexturerd = new TextureRegionDrawable(backtexturer);
            creditstexturerd = new TextureRegionDrawable(creditstexturer);
            langtexturerd = new TextureRegionDrawable(langtexturer);
            eptexturerd = new TextureRegionDrawable(eptexturer);

            soundbutton = new ImageButton(soundtexturerd);
            musicbutton = new ImageButton(musictexturerd);
            backbutton = new ImageButton(backtexturerd);
            creditsbutton = new ImageButton(creditstexturerd);
            langbutton = new ImageButton(langtexturerd);
            epbutton = new ImageButton(eptexturerd);

            stage = new Stage(new ScreenViewport());
            stage.addActor(soundbutton);
            stage.addActor(musicbutton);
            stage.addActor(backbutton);
            stage.addActor(creditsbutton);
            stage.addActor(langbutton);
            stage.addActor(epbutton);
            Gdx.input.setInputProcessor(stage);
        }
    }

    @Override
    public void show(){
        create();
        //if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        soundbutton.setPosition(0, height / 2f - 100 + height / 12f);
        soundbutton.setSize(width / 4f, height / 12f);

        langbutton.setPosition(0, height / 2f - 150 - height / 12f);
        langbutton.setSize(width / 4f, height / 12f);
        if (width>=1080) {
            musicbutton.setPosition(0, height / 2f + 75 - height / 12f);
            musicbutton.setSize(width / 4f, height / 12f);
            epbutton.setPosition(0, height / 2f - 150 - height / 12f - 225);
            epbutton.setSize(width / 4f, height / 12f);
            backbutton.setPosition(0, height / 24f);
            creditsbutton.setPosition(width - 250, height / 24f);
        } else {
            musicbutton.setPosition(0, height / 2f - 20 - height / 12f);
            musicbutton.setSize(width / 4f, height / 12f);
            epbutton.setPosition(0, height / 2f - 280 - height / 12f);
            epbutton.setSize(width / 4f, height / 12f);
            backbutton.setPosition(0, height / 12f);
            backbutton.setSize(width / 4f, height / 12f);
            creditsbutton.setPosition(width - width / 4f + 20, height / 12f);
            creditsbutton.setSize(width / 4f, height / 12f);
        }
        soundbutton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {

                settings.setSound(!settings.isSoundOn());
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);

            }

        });

        musicbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                settings.setMusic(!settings.isMusicOn());
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);

            }
        });

        epbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                settings.setEpilepsy(!settings.getEpilepsy());
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);

            }
        });

        backbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new MainScreen(camera));
            }
        });

        creditsbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                Gdx.input.setInputProcessor(null);
                TrJr.INSTANCE.setScreen(new CreditsScreen(camera));
            }
        });

        langbutton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);

                if (settings.getLanguage()==0)
                    settings.setLanguage(1);
                else
                    settings.setLanguage(0);
            }
        });
    }

    public void update(){
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
        if(settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        else Assets.stopMusic(Assets.mainMenu);
        if (spritey < height/4.5f) spritey+=25;
    }

    @Override
    public void render(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            Gdx.input.setCatchKey(Input.Keys.BACK,true);
            TrJr.INSTANCE.setScreen(new MainScreen(camera));
        }
        update();
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
        optionstext.setPosition(width/2f-234, 0.8f*height);
        optionstext.draw(batch);
        if (width<1080) {
            if (settings.getLanguage()==1){
                if (settings.isSoundOn())
                    TrJr.INSTANCE.rfontCyan2.draw(batch, "Звук ВКЛ", width / 5f * 2, height / 2f - 100 + height / 12f * 2 - 30);
                else
                    TrJr.INSTANCE.rfont2.draw(batch, "Звук ВЫКЛ", width / 5f * 2, height / 2f - 100 + height / 12f * 2 - 30);

                if (settings.isMusicOn())
                    TrJr.INSTANCE.rfontCyan2.draw(batch, "Музыка ВКЛ", width / 5f * 2, height / 2f - height / 12f * 2 + 155);
                else
                    TrJr.INSTANCE.rfont2.draw(batch, "Музыка ВЫКЛ", width / 5f * 2, height / 2f - height / 12f * 2 + 155);

                if (settings.getEpilepsy())
                    TrJr.INSTANCE.rfontCyan2.draw(batch, "Мерцание ВКЛ", width / 5f * 2, height / 2f - height / 12f * 2 - 105);
                else
                    TrJr.INSTANCE.rfont2.draw(batch, "Мерцание ВЫКЛ", width / 5f * 2, height / 2f - height / 12f * 2 - 105);

                TrJr.INSTANCE.rfont2.draw(batch, "Русский", width / 5f * 2, height / 2f - height / 12f * 2 + 25);
            } else {
                if (settings.isSoundOn())
                    TrJr.INSTANCE.fontCyan2.draw(batch, "Sound ON", width / 5f * 2, height / 2f - 100 + height / 12f * 2 - 30);
                else
                    TrJr.INSTANCE.font2.draw(batch, "Sound OFF", width / 5f * 2, height / 2f - 100 + height / 12f * 2 - 30);

                if (settings.isMusicOn())
                    TrJr.INSTANCE.fontCyan2.draw(batch, "Music ON", width / 5f * 2, height / 2f - height / 12f * 2 + 155);
                else
                    TrJr.INSTANCE.font2.draw(batch, "Music OFF", width / 5f * 2, height / 2f - height / 12f * 2 + 155);

                if (settings.getEpilepsy())
                    TrJr.INSTANCE.fontCyan2.draw(batch, "Flashing ON", width / 5f * 2, height / 2f - height / 12f * 2 - 105);
                else
                    TrJr.INSTANCE.font2.draw(batch, "Flashing OFF", width / 5f * 2, height / 2f - height / 12f * 2 - 105);

                TrJr.INSTANCE.font2.draw(batch, "English", width / 5f * 2, height / 2f - height / 12f * 2 + 25);
            }

            TrJr.INSTANCE.fontCyan3.draw(batch, "$", 15, height - 14);
            TrJr.INSTANCE.font3.draw(batch, ""+settings.getMoney(), 35, height - 14);
        }
        else {
            if (settings.getLanguage()==1) {
                if (settings.isSoundOn())
                    TrJr.INSTANCE.rfontCyan.draw(batch, "Звук ВКЛ", width / 6f * 2, height / 2f + 210);
                else
                    TrJr.INSTANCE.rfont.draw(batch, "Звук ВЫКЛ", width / 6f * 2, height / 2f + 210);

                if (settings.isMusicOn())
                    TrJr.INSTANCE.rfontCyan.draw(batch, "Музыка ВКЛ", width / 6f * 2, height / 2f);
                else
                    TrJr.INSTANCE.rfont.draw(batch, "Музыка ВЫКЛ", width / 6f * 2, height / 2f);

                if (settings.getEpilepsy())
                    TrJr.INSTANCE.rfontCyan.draw(batch, "Мерцание ВКЛ", width / 6f * 2, height / 2f - 450);
                else
                    TrJr.INSTANCE.rfont.draw(batch, "Мерцание ВЫКЛ", width / 6f * 2, height / 2f - 450);

                TrJr.INSTANCE.rfont.draw(batch, "Русский", width / 6f * 2, height / 2f - 225);
            } else {
                if (settings.isSoundOn())
                    TrJr.INSTANCE.fontCyan.draw(batch, "Sound ON", width / 6f * 2, height / 2f + 210);
                else
                    TrJr.INSTANCE.font.draw(batch, "Sound OFF", width / 6f * 2, height / 2f + 210);

                if (settings.isMusicOn())
                    TrJr.INSTANCE.fontCyan.draw(batch, "Music ON", width / 6f * 2, height / 2f);
                else
                    TrJr.INSTANCE.font.draw(batch, "Music OFF", width / 6f * 2, height / 2f);

                if (settings.getEpilepsy())
                    TrJr.INSTANCE.fontCyan.draw(batch, "Flashing ON", width / 6f * 2, height / 2f - 450);
                else
                    TrJr.INSTANCE.font.draw(batch, "Flashing OFF", width / 6f * 2, height / 2f - 450);

                TrJr.INSTANCE.font.draw(batch, "English", width / 6f * 2, height / 2f - 225);
            }

            TrJr.INSTANCE.fontCyan2.draw(batch, "$ ", 20, height - 28);
            TrJr.INSTANCE.font2.draw(batch, "" + settings.getMoney(), 55, height - 28);
        }
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    /*
    @Override
    public void resize(int width, int height){
        viewport.update(width, height);
        camera.position.set(width/2f, height, 0);
    }
     */

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void hide(){

    }
}
