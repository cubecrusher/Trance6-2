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

public class ShopScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private TextureRegion bluetexturer, redtexturer, greentexturer, yellowtexturer, mdtexturer, hdtexturer, vdtexturer, money1texturer, money2texturer, backtexturer;
    private TextureRegionDrawable bluetexturerd, redtexturerd, greentexturerd, yellowtexturerd, mdtexturerd, hdtexturerd, vdtexturerd, money1texturerd, money2texturerd, backtexturerd;
    private ImageButton bluebtn, redbtn, greenbtn, yellowbtn, mdbtn, hdbtn, vdbtn, money1btn, money2btn, backbutton;
    private Sprite shop;
    private String shopstats, itemname, itemdesc, itemdesc2;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private int height = TrJr.INSTANCE.getScrH();
    private int width = TrJr.INSTANCE.getScrW();
    private int pressn;
    private Settings settings;
    int n=0;

    public ShopScreen(OrthographicCamera camera){
        this.settings = new Settings();
        this.camera = camera;
        this.camera.position.set(new Vector3(width/2f, height/2f,0));
        this.viewport = new FitViewport(800,400, camera);
        this.shapeRenderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.pressn = 0; //   0 = default;    1-9 = button num
        this.shopstats = settings.getStoreinfo();
        if (settings.getLanguage()==1) {
            this.itemname = "Магазин";
            this.itemdesc = "Нажми на предмет для информации";
        }
        else {
            this.itemname = "Shop";
            this.itemdesc = "Tap on an item to inspect";
        }
        this.itemdesc2 = "";
    }

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

    public void create(){
        Texture shopt = new Texture(Gdx.files.internal("textures/shop.png"));
        shop = new Sprite(shopt);
        Texture bluetexture = new Texture(Gdx.files.internal("textures/new/store/shop-blue.png"));
        Texture redtexture = new Texture(Gdx.files.internal("textures/new/store/shop-red.png"));
        Texture greentexture = new Texture(Gdx.files.internal("textures/new/store/shop-green.png"));
        Texture yellowtexture = new Texture(Gdx.files.internal("textures/new/store/shop-yellow.png"));
        Texture mdtexture = new Texture(Gdx.files.internal("textures/new/store/shop-mdiff.png"));
        Texture hdtexture = new Texture(Gdx.files.internal("textures/new/store/shop-hdiff.png"));
        Texture vdtexture = new Texture(Gdx.files.internal("textures/new/store/shop-vdiff.png"));
        Texture money1texture = new Texture(Gdx.files.internal("textures/new/store/shop-money+.png"));
        Texture money2texture = new Texture(Gdx.files.internal("textures/new/store/shop-money++.png"));
        Texture backtexture = new Texture(Gdx.files.internal("textures/new/back.png"));

        bluetexturer = new TextureRegion(bluetexture);
        redtexturer = new TextureRegion(redtexture);
        greentexturer = new TextureRegion(greentexture);
        yellowtexturer = new TextureRegion(yellowtexture);
        mdtexturer = new TextureRegion(mdtexture);
        hdtexturer = new TextureRegion(hdtexture);
        vdtexturer = new TextureRegion(vdtexture);
        money1texturer = new TextureRegion(money1texture);
        money2texturer = new TextureRegion(money2texture);

        backtexturer = new TextureRegion(backtexture);

        bluetexturerd = new TextureRegionDrawable(bluetexturer);
        redtexturerd = new TextureRegionDrawable(redtexturer);
        greentexturerd = new TextureRegionDrawable(greentexturer);
        yellowtexturerd = new TextureRegionDrawable(yellowtexturer);
        mdtexturerd = new TextureRegionDrawable(mdtexturer);
        hdtexturerd = new TextureRegionDrawable(hdtexturer);
        vdtexturerd = new TextureRegionDrawable(vdtexturer);
        money1texturerd = new TextureRegionDrawable(money1texturer);
        money2texturerd = new TextureRegionDrawable(money2texturer);
        backtexturerd = new TextureRegionDrawable(backtexturer);

        bluebtn = new ImageButton(bluetexturerd);
        redbtn = new ImageButton(redtexturerd);
        greenbtn = new ImageButton(greentexturerd);
        yellowbtn = new ImageButton(yellowtexturerd);
        mdbtn = new ImageButton(mdtexturerd);
        hdbtn = new ImageButton(hdtexturerd);
        vdbtn = new ImageButton(vdtexturerd);
        money1btn = new ImageButton(money1texturerd);
        money2btn = new ImageButton(money2texturerd);
        backbutton = new ImageButton(backtexturerd);

        stage = new Stage(new ScreenViewport());

        stage.addActor(bluebtn);
        stage.addActor(redbtn);
        stage.addActor(greenbtn);
        stage.addActor(yellowbtn);
        stage.addActor(mdbtn);
        stage.addActor(hdbtn);
        stage.addActor(vdbtn);
        stage.addActor(money1btn);
        stage.addActor(money2btn);
        stage.addActor(backbutton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show(){
        if (settings.isMusicOn()) Assets.playMusic(Assets.mainMenu);
        create();

        if (width>=1080) {
            bluebtn.setPosition(100, height / 2f + 175);
            if (settings.getStoreinfo().charAt(0)=='0') bluebtn.getColor().a = .5f;

            redbtn.setPosition(100, height / 2f - 125);
            if (settings.getStoreinfo().charAt(1)=='0') redbtn.getColor().a = .5f;

            greenbtn.setPosition(100, height / 2f - 425);
            if (settings.getStoreinfo().charAt(2)=='0') greenbtn.getColor().a = .5f;

            yellowbtn.setPosition(width/2f - 111, height / 2f + 175);
            if (settings.getStoreinfo().charAt(3)=='0') yellowbtn.getColor().a = .5f;

            mdbtn.setPosition(width/2f - 111, height / 2f - 125);
            if (settings.getStoreinfo().charAt(4)=='0') mdbtn.getColor().a = .5f;

            hdbtn.setPosition(width/2f - 111, height / 2f - 425);
            if (settings.getStoreinfo().charAt(5)=='0') hdbtn.getColor().a = .5f;

            vdbtn.setPosition(width-100-223, height / 2f + 175);
            if (settings.getStoreinfo().charAt(6)=='0') vdbtn.getColor().a = .5f;

            money1btn.setPosition(width-100-223, height / 2f - 125);
            if (settings.getStoreinfo().charAt(7)=='0') money1btn.getColor().a = .5f;

            money2btn.setPosition(width-100-223, height / 2f - 425);
            if (settings.getStoreinfo().charAt(8)=='0') money2btn.getColor().a = .5f;

            backbutton.setPosition(0, height / 24f);
        } else {  // Shop WON'T work on small screens - this is unaligned
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
        }

        bluebtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (pressn==1) {
                    if (settings.getMoney() >= 500 && settings.getStoreinfo().charAt(0) != '1') {
                        settings.setMoney(settings.getMoney() - 500);
                        settings.setStoreinfo(replacechar(shopstats, 0, '1'));
                        shopstats = settings.getStoreinfo();
                    }
                }
                pressn=1;
                if (settings.getLanguage()==1){
                    itemname = "Синий корабль";
                    itemdesc = "500$ - стань синим!";
                    if (settings.getStoreinfo().charAt(0)=='0') itemdesc2 = "Нажми чтобы купить";
                    else {
                        if (settings.getColor()==1) itemdesc2 = "Нажми снова чтобы стать белым";
                        else itemdesc2 = "Нажми снова чтобы стать синим";
                    }
                } else {
                    itemname = "Blue ship";
                    itemdesc = "500$ - become blue!";
                    if (settings.getStoreinfo().charAt(0)=='0') itemdesc2 = "Click to purchase";
                    else {
                        if (settings.getColor()==1) itemdesc2 = "Click again to reset to White";
                        else itemdesc2 = "Click again to become Blue";
                    }
                }
                if (settings.getStoreinfo().charAt(0)=='1') {
                    if (settings.getColor()==1) settings.setColor(0);
                    else settings.setColor(1);
                    bluebtn.getColor().a = 1f;
                }
            }
        });
        redbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (pressn==2) {
                    if (settings.getMoney() >= 500 && settings.getStoreinfo().charAt(1) != '1') {
                        settings.setMoney(settings.getMoney() - 500);
                        settings.setStoreinfo(replacechar(shopstats, 1, '1'));
                        shopstats = settings.getStoreinfo();
                    }
                }
                pressn=2;
                if (settings.getLanguage()==1){
                    itemname = "Красный корабль";
                    itemdesc = "500$ - стань красным!";
                    if (settings.getStoreinfo().charAt(1)=='0') itemdesc2 = "Нажми чтобы купить";
                    else {
                        if (settings.getColor()==2) itemdesc2 = "Нажми снова чтобы стать белым";
                        else itemdesc2 = "Нажми снова чтобы стать красным";
                    }
                } else {
                    itemname = "Red ship";
                    itemdesc = "500$ - become red!";
                    if (settings.getStoreinfo().charAt(1)=='0') itemdesc2 = "Click to purchase";
                    else {
                        if (settings.getColor()==2) itemdesc2 = "Click again to reset to White";
                        else itemdesc2 = "Click again to become Red";
                    }
                }
                if (settings.getStoreinfo().charAt(1)=='1') {
                    if (settings.getColor()==2) settings.setColor(0);
                    else settings.setColor(2);
                    redbtn.getColor().a = 1f;
                }
            }
        });

        greenbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (pressn==3) {
                    if (settings.getMoney() >= 500 && settings.getStoreinfo().charAt(2) != '1') {
                        settings.setMoney(settings.getMoney() - 500);
                        settings.setStoreinfo(replacechar(shopstats, 2, '1'));
                        shopstats = settings.getStoreinfo();
                    }
                }
                pressn=3;
                if (settings.getLanguage()==1){
                    itemname = "Зелёный корабль";
                    itemdesc = "500$ - стань зелёным!";
                    if (settings.getStoreinfo().charAt(2)=='0') itemdesc2 = "Нажми чтобы купить";
                    else {
                        if (settings.getColor()==3) itemdesc2 = "Нажми снова чтобы стать белым";
                        else itemdesc2 = "Нажми снова чтобы стать зелёным";
                    }
                } else {
                    itemname = "Green ship";
                    itemdesc = "500$ - become green!";
                    if (settings.getStoreinfo().charAt(2)=='0') itemdesc2 = "Click to purchase";
                    else {
                        if (settings.getColor()==3) itemdesc2 = "Click again to reset to White";
                        else itemdesc2 = "Click again to become Green";
                    }
                }
                if (settings.getStoreinfo().charAt(2)=='1') {
                    if (settings.getColor()==3) settings.setColor(0);
                    else settings.setColor(3);
                    greenbtn.getColor().a = 1f;
                }
            }
        });

        yellowbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (pressn==4) {
                    if (settings.getMoney() >= 500 && settings.getStoreinfo().charAt(3) != '1') {
                        settings.setMoney(settings.getMoney() - 500);
                        settings.setStoreinfo(replacechar(shopstats, 3, '1'));
                        shopstats = settings.getStoreinfo();
                    }
                }
                pressn=4;
                if (settings.getLanguage()==1){
                    itemname = "Жёлтый корабль";
                    itemdesc = "500$ - стань жёлтым!";
                    if (settings.getStoreinfo().charAt(3)=='0') itemdesc2 = "Нажми чтобы купить";
                    else {
                        if (settings.getColor()==4) itemdesc2 = "Нажми снова чтобы стать белым";
                        else itemdesc2 = "Нажми снова чтобы стать жёлтым";
                    }
                } else {
                    itemname = "Yellow ship";
                    itemdesc = "500$ - become yellow!";
                    if (settings.getStoreinfo().charAt(3)=='0') itemdesc2 = "Click to purchase";
                    else {
                        if (settings.getColor()==4) itemdesc2 = "Click again to reset to White";
                        else itemdesc2 = "Click again to become Yellow";
                    }
                }
                if (settings.getStoreinfo().charAt(3)=='1') {
                    if (settings.getColor()==4) settings.setColor(0);
                    else settings.setColor(4);
                    yellowbtn.getColor().a = 1f;
                }
            }
        });

        mdbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (pressn==5) {
                    if (settings.getMoney() >= 50 && settings.getStoreinfo().charAt(4) != '1') {
                        settings.setMoney(settings.getMoney() - 50);
                        settings.setStoreinfo(replacechar(shopstats, 4, '1'));
                        shopstats = settings.getStoreinfo();
                    }
                }
                pressn=5;
                if (settings.getLanguage()==1){
                    itemname = "Сложность 'Нормальная'";
                    itemdesc = "50$ - 2х денег";
                    if (settings.getStoreinfo().charAt(4)=='0') itemdesc2 = "Нажми чтобы купить";
                } else {
                    itemname = "Normal difficulty";
                    itemdesc = "50$ - 2x money";
                    if (settings.getStoreinfo().charAt(4)=='0') itemdesc2 = "Click to purchase";
                }
                itemdesc2 = "";
                if (settings.getStoreinfo().charAt(4) == '1') mdbtn.getColor().a = 1f;
            }
        });

        hdbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (pressn==6) {
                    if (settings.getMoney() >= 250 && settings.getStoreinfo().charAt(5) != '1') {
                        settings.setMoney(settings.getMoney() - 250);
                        settings.setStoreinfo(replacechar(shopstats, 5, '1'));
                        shopstats = settings.getStoreinfo();
                    }
                }
                pressn=6;
                if (settings.getLanguage()==1){
                    itemname = "Сложность 'Эксперт'";
                    itemdesc = "250$ - 4х денег";
                    if (settings.getStoreinfo().charAt(5)=='0') itemdesc2 = "Нажми чтобы купить";
                } else {
                    itemname = "Expert difficulty";
                    itemdesc = "250$ - 4x money";
                    if (settings.getStoreinfo().charAt(5)=='0') itemdesc2 = "Click to purchase";
                }
                itemdesc2 = "";
                if (settings.getStoreinfo().charAt(5) == '1') hdbtn.getColor().a = 1f;
            }
        });

        vdbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (pressn==7) {
                    if (settings.getMoney() >= 1000 && settings.getStoreinfo().charAt(6) != '1') {
                        settings.setMoney(settings.getMoney() - 1000);
                        settings.setStoreinfo(replacechar(shopstats, 6, '1'));
                        shopstats = settings.getStoreinfo();
                    }
                }
                pressn=7;
                if (settings.getLanguage()==1){
                    itemname = "Сложность 'Хардкор'";
                    itemdesc = "1000$ - 16х денег";
                    if (settings.getStoreinfo().charAt(6)=='0') itemdesc2 = "Нажми чтобы купить";
                } else {
                    itemname = "Cursed difficulty";
                    itemdesc = "1000$ - 16x money";
                    if (settings.getStoreinfo().charAt(6)=='0') itemdesc2 = "Click to purchase";
                }
                itemdesc2 = "";
                if (settings.getStoreinfo().charAt(6) == '1') vdbtn.getColor().a = 1f;
            }
        });

        money1btn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (pressn==8) {
                    if (settings.getMoney() >= 500 && settings.getStoreinfo().charAt(7) != '1') {
                        settings.setMoney(settings.getMoney() - 500);
                        settings.setStoreinfo(replacechar(shopstats, 7, '1'));
                        shopstats = settings.getStoreinfo();
                    }
                }
                pressn=8;
                if (settings.getLanguage()==1){
                    itemname = "Зарплата+";
                    itemdesc = "500$ - добавка 50%";
                    if (settings.getStoreinfo().charAt(7)=='0') itemdesc2 = "Нажми чтобы купить";
                } else {
                    itemname = "Money+";
                    itemdesc = "500$ - income increase by 50%";
                    if (settings.getStoreinfo().charAt(7)=='0') itemdesc2 = "Click to purchase";
                }
                itemdesc2 = "";
                if (settings.getStoreinfo().charAt(7) == '1') money1btn.getColor().a = 1f;
            }
        });

        money2btn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (settings.isSoundOn()) Assets.playSound(Assets.blip1);
                if (pressn==9) {
                    if (settings.getMoney() >= 1500 && settings.getStoreinfo().charAt(8) != '1') {
                        settings.setMoney(settings.getMoney() - 1500);
                        settings.setStoreinfo(replacechar(shopstats, 8, '1'));
                        shopstats = settings.getStoreinfo();
                    }
                }
                pressn=9;
                if (settings.getLanguage()==1){
                    itemname = "Зарплата++";
                    itemdesc = "1500$ - добавка 250%";
                    if (settings.getStoreinfo().charAt(8)=='0') itemdesc2 = "Нажми чтобы купить";
                } else {
                    itemname = "Money++";
                    itemdesc = "1500$ - income increase by 250%";
                    if (settings.getStoreinfo().charAt(8)=='0') itemdesc2 = "Click to purchase";
                }
                itemdesc2 = "";
                if (settings.getStoreinfo().charAt(8) == '1') money2btn.getColor().a = 1f;
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
        shop.setPosition(width / 2f - 142, height - height / 4.5f);
        shop.draw(batch);
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
                TrJr.INSTANCE.rfont2.draw(batch, ""+itemdesc2, 55, height / 2f - 425 - 175);
            } else {
                TrJr.INSTANCE.fontCyan.draw(batch, ""+itemname, 55, height / 2f - 425 - 50);
                TrJr.INSTANCE.font2.draw(batch, ""+itemdesc, 55, height / 2f - 425 - 125);
                TrJr.INSTANCE.font2.draw(batch, ""+itemdesc2, 55, height / 2f - 425 - 175);
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
