package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
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

public class AcScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private TextureRegion ac1kr, ac1mbr, ac10kmr, accursedtr, achardtr, acmedtr, aceasytr, acdeathtr, acsecretr, backtexturer;
    private TextureRegionDrawable ac1krd, ac1mbrd, ac10kmrd, accursedtrd, achardtrd, acmedtrd, aceasytrd, acdeathtrd, acsecretrd, backtexturerd;
    private ImageButton ac1kbtn, ac1mbbtn, ac10kbtn, accursedbtn, achardbtn, acmedbtn, aceasybtn, acdeathbtn, acsecretbtn, backbutton;
    private Sprite mile;
    private String acinfo, itemname, itemdesc;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private int height = TrJr.INSTANCE.getScrH();
    private int width = TrJr.INSTANCE.getScrW();
    private Settings settings;
    int n=0;

    public String replacechar(String str, int index, char replace){
        if(str==null){
            return str;
        }else if(index<0 || index>=str.length()){
            return str;
        }
        char[] chars = str.toCharArray();
        chars[index] = replace;
        return String.valueOf(chars);
    }

    public AcScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.camera.position.set(new Vector3(width/2f, height/2f,0));
        this.viewport = new FitViewport(800,400, camera);
        this.shapeRenderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.acinfo = settings.getAcinfo();
        if (settings.getLanguage()==1) {
            this.itemname = "Достижения";
            this.itemdesc = "Нажми на предмет для информации";
        }
        else {
            this.itemname = "Milestones";
            this.itemdesc = "Tap on an item to inspect";
        }
    }

    public void create(){
        Texture milet = new Texture(Gdx.files.internal("textures/achievements.png"));
        mile = new Sprite(milet);
        Texture ac1k = new Texture(Gdx.files.internal("textures/new/achieve/ac-1kdeaths.png"));
        Texture ac1mb = new Texture(Gdx.files.internal("textures/new/achieve/ac-1mbucks.png"));
        Texture ac10km = new Texture(Gdx.files.internal("textures/new/achieve/ac-10kbucks.png"));
        Texture accursed = new Texture(Gdx.files.internal("textures/new/achieve/ac-cursedtime.png"));
        Texture achard = new Texture(Gdx.files.internal("textures/new/achieve/ac-hardtime.png"));
        Texture acmed = new Texture(Gdx.files.internal("textures/new/achieve/ac-mediumtime.png"));
        Texture aceasy = new Texture(Gdx.files.internal("textures/new/achieve/ac-easytime.png"));
        Texture acdeath = new Texture(Gdx.files.internal("textures/new/achieve/ac-instantdeath.png"));
        Texture acsecret = new Texture(Gdx.files.internal("textures/new/achieve/ac-secret.png"));
        Texture acunk = new Texture(Gdx.files.internal("textures/new/achieve/ac-unknown.png"));
        Texture backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));

        ac1kr = new TextureRegion(ac1k);
        ac1mbr = new TextureRegion(ac1mb);
        ac10kmr = new TextureRegion(ac10km);
        accursedtr = new TextureRegion(accursed);
        achardtr = new TextureRegion(achard);
        acmedtr = new TextureRegion(acmed);
        aceasytr = new TextureRegion(aceasy);
        acdeathtr = new TextureRegion(acdeath);
        if (settings.getAcinfo().charAt(8)==1) acsecretr = new TextureRegion(acsecret);
        else acsecretr = new TextureRegion(acunk);

        backtexturer = new TextureRegion(backtexture);

        ac1krd = new TextureRegionDrawable(ac1kr);
        ac1mbrd = new TextureRegionDrawable(ac1mbr);
        ac10kmrd = new TextureRegionDrawable(ac10kmr);
        accursedtrd = new TextureRegionDrawable(accursedtr);
        achardtrd = new TextureRegionDrawable(achardtr);
        acmedtrd = new TextureRegionDrawable(acmedtr);
        aceasytrd = new TextureRegionDrawable(aceasytr);
        acdeathtrd = new TextureRegionDrawable(acdeathtr);
        acsecretrd = new TextureRegionDrawable(acsecretr);
        backtexturerd = new TextureRegionDrawable(backtexturer);

        ac1kbtn = new ImageButton(ac1krd);
        ac1mbbtn = new ImageButton(ac1mbrd);
        ac10kbtn = new ImageButton(ac10kmrd);
        accursedbtn = new ImageButton(accursedtrd);
        achardbtn = new ImageButton(achardtrd);
        acmedbtn = new ImageButton(acmedtrd);
        aceasybtn = new ImageButton(aceasytrd);
        acdeathbtn = new ImageButton(acdeathtrd);
        acsecretbtn = new ImageButton(acsecretrd);
        backbutton = new ImageButton(backtexturerd);

        stage = new Stage(new ScreenViewport());

        stage.addActor(ac1kbtn);
        stage.addActor(ac1mbbtn);
        stage.addActor(ac10kbtn);
        stage.addActor(accursedbtn);
        stage.addActor(achardbtn);
        stage.addActor(acmedbtn);
        stage.addActor(aceasybtn);
        stage.addActor(acdeathbtn);
        stage.addActor(acsecretbtn);
        stage.addActor(backbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show(){
        if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        create();
        settings.setAcinfo("000000000");
        if (settings.getPlays()>=1000) {
            settings.setAcinfo(replacechar(acinfo,6,'1'));
            acinfo = settings.getAcinfo();
        }
        if (settings.getMoney()>=100000) {
            settings.setAcinfo(replacechar(acinfo,5,'1'));
            acinfo = settings.getAcinfo();
        }
        if (settings.getMoney()>=10000) {
            settings.setAcinfo(replacechar(acinfo,4,'1'));
            acinfo = settings.getAcinfo();
        }
        if (settings.getcHighScore()>=10) {
            settings.setAcinfo(replacechar(acinfo,3,'1'));
            acinfo = settings.getAcinfo();
        }
        if (settings.gethHighScore()>=45) {
            settings.setAcinfo(replacechar(acinfo,2,'1'));
            acinfo = settings.getAcinfo();
        }
        if (settings.getnHighScore()>=60) {
            settings.setAcinfo(replacechar(acinfo,1,'1'));
            acinfo = settings.getAcinfo();
        }
        if (settings.geteHighScore()>=120) {
            settings.setAcinfo(replacechar(acinfo,0,'1'));
            acinfo = settings.getAcinfo();
        }

        if (width>=1080) {
            ac1kbtn.setPosition(100, height / 2f + 175);
            if (settings.getAcinfo().charAt(6)=='0') ac1kbtn.getColor().a = .5f;

            ac1mbbtn.setPosition(100, height / 2f - 125);
            if (settings.getAcinfo().charAt(5)=='0') ac1mbbtn.getColor().a = .5f;

            ac10kbtn.setPosition(100, height / 2f - 425);
            if (settings.getAcinfo().charAt(4)=='0') ac10kbtn.getColor().a = .5f;

            accursedbtn.setPosition(width/2f - 111, height / 2f + 175);
            if (settings.getAcinfo().charAt(3)=='0') accursedbtn.getColor().a = .5f;

            achardbtn.setPosition(width/2f - 111, height / 2f - 125);
            if (settings.getAcinfo().charAt(2)=='0') achardbtn.getColor().a = .5f;

            acmedbtn.setPosition(width/2f - 111, height / 2f - 425);
            if (settings.getAcinfo().charAt(1)=='0') acmedbtn.getColor().a = .5f;

            aceasybtn.setPosition(width-100-223, height / 2f + 175);
            if (settings.getAcinfo().charAt(0)=='0') aceasybtn.getColor().a = .5f;

            acdeathbtn.setPosition(width-100-223, height / 2f - 125);
            if (settings.getAcinfo().charAt(7)=='0') acdeathbtn.getColor().a = .5f;

            acsecretbtn.setPosition(width-100-223, height / 2f - 425);
            if (settings.getAcinfo().charAt(8)=='0') acsecretbtn.getColor().a = .5f;

            backbutton.setPosition(0, height / 24f);
        } else {
            /*
            bluebtn.setPosition(-5, height / 2f);
            bluebtn.setSize(width-80,height/12f);

            redbtn.setPosition(-5, height / 2f - 100);
            redbtn.setSize(width-80,height/12f);

            greenbtn.setPosition(-5, height / 2f - 200);
            greenbtn.setSize(width-80,height/12f);

            yellowbtn.setPosition(-5, height / 2f - 300);
            yellowbtn.setSize(width-80,height/12f);

            backbutton.setPosition(-20, height / 12f);
            backbutton.setSize(width / 4f, height / 12f);

             */
        }

        ac1kbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);

                if (settings.getLanguage()==1){
                    itemname = "Доминация!";
                    itemdesc = "Умереть 1к раз";
                } else {
                    itemname = "Domination!";
                    itemdesc = "Die 1k times";
                }

            }
        });
        ac1mbbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (settings.getLanguage()==1){
                    itemname = "Олигарх";
                    itemdesc = "Имеется 100к$ в запасе";
                } else {
                    itemname = "Elon Musk";
                    itemdesc = "Have 100k$ at once";
                }
            }
        });

        ac10kbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (settings.getLanguage()==1){
                    itemname = "Средний класс";
                    itemdesc = "Имеется 10к$ в запасе";
                } else {
                    itemname = "Can afford milk";
                    itemdesc = "Have 10k$ at once";
                }
            }
        });

        accursedbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (settings.getLanguage()==1){
                    itemname = "Король Хардкора";
                    itemdesc = "Выжить 10с на сложности 'Хардкор'";
                } else {
                    itemname = "Trollge";
                    itemdesc = "Survive 10s on Cursed";
                }
            }
        });

        achardbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (settings.getLanguage()==1){
                    itemname = "Труъ Эксперт";
                    itemdesc = "Выжить 45с на сложности 'Эксперт'";
                } else {
                    itemname = "Certified Expert";
                    itemdesc = "Survive 45s on Expert";
                }
            }
        });

        acmedbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (settings.getLanguage()==1){
                    itemname = "Выше среднего";
                    itemdesc = "Выжить 60с на сложности 'Нормальная'";
                } else {
                    itemname = "Above average";
                    itemdesc = "Survive 60s on Normal";
                }
            }
        });

        aceasybtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (settings.getLanguage()==1){
                    itemname = "Окончил 1й класс";
                    itemdesc = "Выжить 120с на сложности 'Новичок'";
                } else {
                    itemname = "Passed the ABCs";
                    itemdesc = "Survive 120s on Beginner";
                }
            }
        });

        acdeathbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (settings.getLanguage()==1){
                    itemname = "Слоупок";
                    itemdesc = "Умереть сразу";
                } else {
                    itemname = "Slowpoke";
                    itemdesc = "Die instantly";
                }
            }
        });

        acsecretbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (settings.getAcinfo().charAt(8)==1) {
                    if (settings.getLanguage() == 1) {
                        itemname = "Секрет";
                        itemdesc = "Разведчик - шпион!";
                    } else {
                        itemname = "Secret";
                        itemdesc = "That scout is a bloody spy!";
                    }
                } else {
                    if (settings.getLanguage() == 1) {
                        itemname = "Секрет";
                        itemdesc = "...";
                    } else {
                        itemname = "Secret";
                        itemdesc = "...";
                    }
                }
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

    public void update(){
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
    }

    @Override
    public void render(float delta){
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
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.begin();
        mile.setPosition(width / 2f - 345, height - height / 4.5f);
        mile.draw(batch);
        if (width < 1080) {
            TrJr.INSTANCE.fontCyan3.draw(batch, "$", 15, height - 14);
            TrJr.INSTANCE.font3.draw(batch, "" + settings.getMoney(), 35, height - 14);

            if (settings.getLanguage()==1){
                TrJr.INSTANCE.rfontCyan.draw(batch, itemname, 20, height / 2f - 425 - 50);
                TrJr.INSTANCE.rfont2.draw(batch, itemdesc, 55, height / 2f - 425 - 75);
            } else {
                TrJr.INSTANCE.fontCyan.draw(batch, itemname, 20, height / 2f - 425 - 50);
                TrJr.INSTANCE.font2.draw(batch, itemdesc, 55, height / 2f - 425 - 75);
            }

        } else {
            TrJr.INSTANCE.fontCyan2.draw(batch, "$ ", 20, height - 28);
            TrJr.INSTANCE.font2.draw(batch, ""+settings.getMoney(), 55, height - 28);

            if (settings.getLanguage()==1){
                TrJr.INSTANCE.rfontCyan.draw(batch, ""+itemname, 55, height / 2f - 425 - 50);
                TrJr.INSTANCE.rfont2.draw(batch, ""+itemdesc, 55, height / 2f - 425 - 125);
            } else {
                TrJr.INSTANCE.fontCyan.draw(batch, ""+itemname, 55, height / 2f - 425 - 50);
                TrJr.INSTANCE.font2.draw(batch, ""+itemdesc, 55, height / 2f - 425 - 125);
            }
        }
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
