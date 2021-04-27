package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.text.DecimalFormat;

import obj.*;

public class GameScreen extends ScreenAdapter {
    protected OrthographicCamera camerag;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Settings settings;

    protected Player player;
    protected Obstacle obstacle;
    protected Obstacle2 obstacle2;
    protected Obstacle3 obstacle3;
    protected Obstacle4 obstacle4;
    protected PatternMidStraight patternMidStraight;
    protected PatternSides patternSides;
    protected PatternSlideL patternSlideL;
    protected PatternSlideR patternSlideR;
    protected PatternTriStraight patternTriStraight;
    protected PatternDiStraight patternDiStraight;
    protected PatternMiddle patternMiddle;
    protected PatternZigzagL patternZigzagL;
    protected PatternZigzagR patternZigzagR;
    protected PatternDoubleSlide patternDoubleSlide;

    private final int width = TrJr.INSTANCE.getScrW();
    private final int height = TrJr.INSTANCE.getScrH();
    private final double rng = Math.random();
    private double prng = 0;

    protected float playTime = 0.00f;
    protected float highScore = 0.00f;
    protected float totalTime;
    public float velocity;
    public Float highScoreg;
    public Float strDouble;

    protected int bpm;
    protected int musicDur; //in seconds
    protected int musicPos;
    protected int musicN;

    public boolean hasCollided;
    protected int n, plays;
    protected int pattern;
    protected boolean isOut;


    public GameScreen(OrthographicCamera camera){
        this.camerag = camera;
        this.settings = new Settings();
        this.camerag.position.set(new Vector3(width/2f, height/2f,0));
        this.batch = new SpriteBatch();
        this.n = 1;
        this.pattern = (int)(Math.random()*10);
        this.player = new Player(this);
        this.hasCollided = false;
        this.velocity = TrJr.INSTANCE.getScrH()/120f;
        this.isOut = false;
        this.patternMiddle = new PatternMiddle(this);
        this.patternMidStraight = new PatternMidStraight(this);
        this.patternSides = new PatternSides(this);
        this.patternSlideL = new PatternSlideL(this);
        this.patternTriStraight = new PatternTriStraight(this);
        this.patternDiStraight = new PatternDiStraight(this);
        this.shapeRenderer = new ShapeRenderer();
        this.highScoreg = settings.getHighScore();
        this.plays = settings.getPlays();
        this.totalTime = settings.getTotal();
    }

    // Play music, add "Back" functionalities
    @Override
    public void show(){
        plays++;
        Assets.stopMusic(Assets.mainMenu);
        if (settings.isMusicOn()) {
            if (rng<=0.1) {
                Assets.fireAura.play();
                bpm = 180;
                musicDur = 213;
                musicN = 0;
            }
            else if (rng<=0.2 && rng>0.1) {
                Assets.chaozFantasy.play();
                bpm = 108;
                musicDur = 121;
                musicN = 1;
            }
            else if (rng<=0.3 && rng>0.2) {
                Assets.fireFly.play();
                bpm = 140;
                musicDur = 258;
                musicN = 2;
            }
            else if (rng<=0.4 && rng>0.3) {
                Assets.uD.play();
                bpm = 140;
                musicDur = 198;
                musicN = 3;
            }
            else if (rng<=0.5 && rng>0.4) {
                Assets.dogHouse.play();
                bpm = 140;
                musicDur = 198;
                musicN = 3;
            }
            else if (rng<=0.6 && rng>0.5) {
                Assets.tireDmg.play();
                bpm = 140;
                musicDur = 198;
                musicN = 3;
            }
            else if (rng<=0.8 && rng>0.7) {
                Assets.shinyTech.play();
                bpm = 160;
                musicDur = 218;
                musicN = 4;
            }
            else if (rng<=0.9 && rng>0.8) {
                Assets.archetype.play();
                bpm = 160;
                musicDur = 218;
                musicN = 4;
            }
            else {
                Assets.harmfatal.play();
                bpm = 160;
                musicDur = 253;
                musicN = 5;
            }
        }
    }

    // Update all entities, check for collision
    public void update(){
        batch.setProjectionMatrix(camerag.combined);
            this.player.update();
            checkOut();
            this.camerag.update();
         switch (pattern) {
            case 1:
                patternSlideL.update();
                break;

            case 2:
                patternSides.update();
                break;

            case 3:
                patternDiStraight.update();
                break;

            case 4:
                patternMiddle.update();
                break;

            case 5:
                patternTriStraight.update();
                break;

            case 6:
                patternMidStraight.update();
                break;
        }
            if     (Intersector.intersectPolygons(player.polygon, patternMidStraight.polygon, null) ||
                    Intersector.intersectPolygons(player.polygon, patternTriStraight.polygonL, null) ||
                    Intersector.intersectPolygons(player.polygon, patternTriStraight.polygonR, null) ||
                    Intersector.intersectPolygons(player.polygon, patternDiStraight.polygonL, null) ||
                    Intersector.intersectPolygons(player.polygon, patternDiStraight.polygonM, null) ||
                    Intersector.intersectPolygons(player.polygon, patternDiStraight.polygonR, null) ||
                    Intersector.intersectPolygons(player.polygon, patternSides.polygonL, null) ||
                    Intersector.intersectPolygons(player.polygon, patternSides.polygonR, null) ||
                    Intersector.intersectPolygons(player.polygon, patternMiddle.polygon, null) ||
                    Intersector.intersectPolygons(player.polygon, patternMiddle.polygon, null))
                hasCollided = true;
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            Gdx.input.setCatchKey(Input.Keys.BACK,true);
            TrJr.INSTANCE.setScreen(new MainScreen(camerag));
            if (n<=2) {
                shapeRenderer.setAutoShapeType(true);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.WHITE);
                shapeRenderer.rect(0, 0, width, height);
                shapeRenderer.end();
                n++;
            }
            Assets.stopAllGameMusic();
            TrJr.INSTANCE.setScreen(new MainScreen(camerag));
        }
        velocity+=0.005;
    }

    // Render everything
    @Override
    public void render(float delta) {
        EndScreen.newBest = false;
            if (!hasCollided) {
                update();
                this.playTime += delta;
                this.strDouble = ((int)(playTime*100))/100f;
                if (this.strDouble > settings.getHighScore())
                    highScore = playTime;
                musicPos += this.strDouble;
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                this.patternDiStraight.velocity = velocity;
                this.patternTriStraight.velocity = velocity;
                this.patternMiddle.velocity = velocity;
                this.patternMidStraight.velocity = velocity;
                this.patternSlideL.velocity = velocity;
                this.patternSides.velocity = velocity;

                String velocityg = String.format("%.0f", velocity * Gdx.graphics.getFramesPerSecond());

                this.player.render();
                 switch (pattern) {
                    case 1:
                        patternSlideL.render();
                        break;

                    case 2:
                        patternSides.render();
                        break;

                    case 3:
                        patternDiStraight.render();
                        break;

                    case 4:
                        patternMiddle.render();
                        break;

                    case 5:
                        patternTriStraight.render();
                        break;

                    case 6:
                        patternMidStraight.render();
                        break;
                }

                if (playTime <= 0.075) {
                    shapeRenderer.setAutoShapeType(true);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.setColor(Color.WHITE);
                    shapeRenderer.rect(0, 0, width, height);
                    shapeRenderer.end();
                }
                if (playTime <= 4.8) {
                    shapeRenderer.setAutoShapeType(true);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.setColor(Color.BLACK);
                    shapeRenderer.rect(2, height / 2f - 10 - 2, width, 10);
                    shapeRenderer.setColor(Color.WHITE);
                    shapeRenderer.rect(0, height / 2f - 10, width, 10);
                    shapeRenderer.end();
                } else {
                    if (player.oob) {
                        shapeRenderer.setAutoShapeType(true);
                        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                        shapeRenderer.setColor(Color.BLACK);
                        shapeRenderer.rect(2, height / 2f - 10 - 2, width, 10);
                        shapeRenderer.setColor(Color.WHITE);
                        shapeRenderer.rect(0, height / 2f - 10, width, 10);
                        shapeRenderer.end();
                    }
                }

                batch.begin();
                if (TrJr.INSTANCE.getScrW()<1080) {
                    Assets.gui2.draw(batch, "" + strDouble, 50, height - 50);
                    Assets.gui.draw(batch, "" + strDouble, 45, height - 45);
                } else {
                    Assets.gui2.draw(batch, "" + strDouble, 85, height - 85);
                    Assets.gui.draw(batch, "" + strDouble, 80, height - 80);
                }
                if (playTime < settings.getHighScore()) {
                    if (TrJr.INSTANCE.getScrW() < 1080) {
                        Assets.gui2Small.draw(batch, "B: " + settings.getHighScore(), 50, height - 125);
                        Assets.guiSmall.draw(batch, "B: " + settings.getHighScore(), 48, height - 123);
                    } else {
                        Assets.gui2Small.draw(batch, "B: " + settings.getHighScore(), 82, height - 162);
                        Assets.guiSmall.draw(batch, "B: " + settings.getHighScore(), 80, height - 160);
                    }
                } else {
                    if (TrJr.INSTANCE.getScrW() < 1080) {
                        Assets.gui2Small.draw(batch, "New best!", 50, height - 125);
                        Assets.guiSmall.draw(batch, "New best!", 48, height - 123);
                    } else {
                        Assets.gui2Small.draw(batch, "New best!", 82, height - 162);
                        Assets.guiSmall.draw(batch, "New best!", 80, height - 160);
                    }
                }

                if (playTime <= 4.8) {
                    Assets.gui2Small.draw(batch, "Control area", 22, height / 2f - 2 + 40);
                    Assets.guiSmall.draw(batch, "Control area", 20, height / 2f + 40);
                }

                if (settings.isFpsOn()) {
                    Assets.gui2Small.draw(batch, "F: " + Gdx.graphics.getFramesPerSecond(), 82, height - 197);
                    Assets.guiSmall.draw(batch, "F: " + Gdx.graphics.getFramesPerSecond(), 80, height - 195);
                }

                if (settings.isSpeedOn()) {
                    Assets.gui2Small.draw(batch, "V: " + velocityg, 82, height - 232);
                    Assets.guiSmall.draw(batch, "V: " + velocityg, 80, height - 230);
                }
                if (player.oob && playTime > 4.8) {
                    Assets.gui2Small.draw(batch, "Move down", 22, height / 2f - 2 + 40);
                    Assets.guiSmall.draw(batch, "Move down", 20, height / 2f + 40);
                }
                batch.end();
            } else {
                this.player.renderOver();
                Assets.stopAllGameMusic();
                if (settings.isSoundOn()) Assets.playSound(Assets.gameOver);
                if (player.nn > 6000) {
                    settings.setPlays(plays);
                    EndScreen.time = strDouble;
                    totalTime+=strDouble;
                    settings.setTotal(totalTime);
                    if (playTime > this.highScoreg) {
                        settings.setHighScore(strDouble);
                        EndScreen.newBest = true;
                    }
                    TrJr.INSTANCE.setScreen(new EndScreen(camerag));
                }
            }
    }

    public void checkOut() {

        if (pattern==1) {
            patternSlideL.update();
            patternSlideL.render();
            if (patternSlideL.isOut) {
                this.isOut = true;
                pattern = (int) (Math.random() * 10);
            }
        }
        if (pattern==2) {
            patternSides.update();
            patternSides.render();
            if (patternSides.isOut) {
                this.isOut = true;
                pattern = (int) (Math.random() * 10);
            }
        }
        if (pattern==3) {
            patternDiStraight.update();
            patternDiStraight.render();
            if (patternDiStraight.isOut) {
                this.isOut = true;
                pattern = (int) (Math.random() * 10);
            }
        }
        if (pattern==4) {
            patternMiddle.update();
            patternMiddle.render();
            if (patternMiddle.isOut) {
                this.isOut = true;
                pattern = (int) (Math.random() * 10);
            }
        }
        if (pattern==5) {
            patternTriStraight.update();
            patternTriStraight.render();
            if (patternTriStraight.isOut) {
                this.isOut = true;
                pattern = (int) (Math.random() * 10);
            }
        }
        if (pattern==6) {
            patternMidStraight.update();
            patternMidStraight.render();
            if (patternMidStraight.isOut) {
                this.isOut = true;
                pattern = (int) (Math.random() * 10);
            }
        }
        else {
            patternMidStraight.update();
            patternMidStraight.render();
            if (patternMidStraight.isOut) {
                this.isOut = true;
                pattern = (int) (Math.random() * 10);
            }
        }


            if (this.isOut) {
                patternSlideL.resetPos();
                patternSides.resetPos();
                patternDiStraight.resetPos();
                patternMiddle.resetPos();
                patternTriStraight.resetPos();
                patternMidStraight.resetPos();

                patternSlideL.isOut = false;
                patternSides.isOut = false;
                patternDiStraight.isOut  = false;
                patternMiddle.isOut = false;
                patternTriStraight.isOut = false;
                patternMidStraight.isOut = false;
                isOut = false;
            }
    }
}
