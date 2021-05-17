package com.cubecrusher.trancej;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;

import obj.Obstacle;
import obj.Obstacle2;
import obj.Obstacle3;
import obj.Obstacle4;
import obj.Player;

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

    private final int width = TrJr.INSTANCE.getScrW();
    private final int height = TrJr.INSTANCE.getScrH();
    private final double rng = Math.random();
    private double prng = 0;

    protected float playTime = -2.00f;
    protected float highScore = 0.00f;
    protected float totalTime;
    public float velocity;
    public Float ehighScoreg, nhighScoreg, hhighScoreg, chighScoreg;
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
        this.obstacle = new Obstacle(this);
        this.obstacle2 = new Obstacle2(this);
        this.obstacle3 = new Obstacle3(this);
        this.obstacle4 = new Obstacle4(this);
        this.hasCollided = false;
        this.velocity = height/120f;
        this.isOut = false;
        this.shapeRenderer = new ShapeRenderer();
        this.ehighScoreg = settings.geteHighScore();
        this.nhighScoreg = settings.getnHighScore();
        this.hhighScoreg = settings.gethHighScore();
        this.chighScoreg = settings.getcHighScore();
        this.plays = settings.getPlays();
        this.totalTime = settings.getTotal();
    }

    @Override
    public void show(){
        plays++;

    }

    public void update(){
        Assets.stopMusic(Assets.mainMenu);
        if (playTime>=0) {
            if (settings.isMusicOn()) {
                if (rng <= 0.1) {
                    Assets.fireAura.play();
                    bpm = 180;
                    musicDur = 213;
                    musicN = 0;
                } else if (rng <= 0.2 && rng > 0.1) {
                    Assets.chaozFantasy.play();
                    bpm = 108;
                    musicDur = 121;
                    musicN = 1;
                } else if (rng <= 0.3 && rng > 0.2) {
                    Assets.fireFly.play();
                    bpm = 140;
                    musicDur = 258;
                    musicN = 2;
                } else if (rng <= 0.4 && rng > 0.3) {
                    Assets.uD.play();
                    bpm = 140;
                    musicDur = 198;
                    musicN = 3;
                } else if (rng <= 0.5 && rng > 0.4) {
                    Assets.tireDmg.play();
                    bpm = 140;
                    musicDur = 198;
                    musicN = 3;
                } else if (rng <= 0.6 && rng > 0.5) {
                    Assets.shinyTech.play();
                    bpm = 140;
                    musicDur = 198;
                    musicN = 3;
                } else if (rng <= 0.8 && rng > 0.7) {
                    Assets.archetype.play();
                    bpm = 160;
                    musicDur = 218;
                    musicN = 4;
                } else if (rng <= 0.9 && rng > 0.8) {
                    Assets.harmfatal.play();
                    bpm = 160;
                    musicDur = 218;
                    musicN = 4;
                } else {
                    Assets.uD.play();
                    bpm = 160;
                    musicDur = 253;
                    musicN = 5;
                }
            }
        }
        batch.setProjectionMatrix(camerag.combined);
        this.player.update();
        if (playTime>=0) {
            this.obstacle.update();
            this.obstacle2.update();
            this.obstacle3.update();
            this.obstacle4.update();
        }
        this.camerag.update();
        if (Intersector.intersectPolygons(player.polygon, obstacle.polygon, null) || Intersector.intersectPolygons(player.polygon, obstacle2.polygon, null) || Intersector.intersectPolygons(player.polygon, obstacle3.polygon, null) || Intersector.intersectPolygons(player.polygon, obstacle4.polygon, null))
            hasCollided = true;
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            Gdx.input.setCatchKey(Input.Keys.BACK,true);
            TrJr.INSTANCE.setScreen(new MainScreen(camerag));
            if (n<=2) {
                shapeRenderer.setAutoShapeType(true);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.DARK_GRAY);
                shapeRenderer.rect(0, 0, width, height);
                shapeRenderer.end();
                n++;
            }
            Assets.stopAllGameMusic();
            TrJr.INSTANCE.setScreen(new MainScreen(camerag));
        }
        if (settings.getDifficulty().matches("Beginner")) velocity+=0.0025;
        if (settings.getDifficulty().matches("Medium")) velocity+=0.005;
        if (settings.getDifficulty().matches("Expert")) velocity+=0.01;
        if (settings.getDifficulty().matches("Cursed")) velocity+=0.05;
    }

    @Override
    public void render(float delta) {
        EndScreen.newBest = false;
        settings.setScoreSent(false);
            if (!hasCollided) {
                update();
                this.playTime += delta;
                this.strDouble = ((int)(playTime*100))/100f;
                if (settings.getDifficulty().equals("Beginner")) {
                    if (this.strDouble > settings.geteHighScore()) highScore = playTime;
                }
                if (settings.getDifficulty().equals("Medium")) {
                    if (this.strDouble > settings.getnHighScore()) highScore = playTime;
                }
                if (settings.getDifficulty().equals("Expert")) {
                    if (this.strDouble > settings.gethHighScore()) highScore = playTime;
                }
                if (settings.getDifficulty().equals("Cursed")) {
                    if (this.strDouble > settings.getcHighScore()) highScore = playTime;
                }
                musicPos += this.strDouble;
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                if (playTime <= -1.925) {
                    shapeRenderer.setAutoShapeType(true);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.setColor(Color.DARK_GRAY);
                    shapeRenderer.rect(0, 0, width, height);
                    shapeRenderer.end();
                }


                this.obstacle.velocity = velocity;
                this.obstacle2.velocity = velocity;
                this.obstacle3.velocity = velocity;
                this.obstacle4.velocity = velocity;


                String velocityg = String.format("%.0f", velocity * Gdx.graphics.getFramesPerSecond());

                if (playTime>=0) {
                    this.obstacle.render();
                    this.obstacle2.render();
                    this.obstacle3.render();
                    this.obstacle4.render();
                }
                this.player.render();

                if (playTime <= 0) {
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
                    TrJr.INSTANCE.font2.draw(batch, "" + strDouble, 45, height - 45);
                } else {
                    TrJr.INSTANCE.font.draw(batch, "" + strDouble, 80, height - 80);
                }
                if (settings.getDifficulty().equals("Beginner")) {
                    if (playTime < settings.geteHighScore()) {
                        if (TrJr.INSTANCE.getScrW() < 1080) {
                            TrJr.INSTANCE.font3.draw(batch, "B: " + settings.geteHighScore(), 48, height - 90);
                        } else {
                            TrJr.INSTANCE.font2.draw(batch, "B: " + settings.geteHighScore(), 80, height - 160);
                        }
                    } else {
                        if (TrJr.INSTANCE.getScrW() < 1080) {
                            TrJr.INSTANCE.fontCyan3.draw(batch, "New best!", 48, height - 90);
                        } else {
                            TrJr.INSTANCE.fontCyan2.draw(batch, "New best!", 80, height - 160);
                        }
                    }
                }
                if (settings.getDifficulty().equals("Medium")) {
                    if (playTime < settings.getnHighScore()) {
                        if (TrJr.INSTANCE.getScrW() < 1080) {
                            TrJr.INSTANCE.font3.draw(batch, "B: " + settings.getnHighScore(), 48, height - 90);
                        } else {
                            TrJr.INSTANCE.font2.draw(batch, "B: " + settings.getnHighScore(), 80, height - 160);
                        }
                    } else {
                        if (TrJr.INSTANCE.getScrW() < 1080) {
                            TrJr.INSTANCE.fontCyan3.draw(batch, "New best!", 48, height - 90);
                        } else {
                            TrJr.INSTANCE.fontCyan2.draw(batch, "New best!", 80, height - 160);
                        }
                    }
                }
                if (settings.getDifficulty().equals("Expert")) {
                    if (playTime < settings.gethHighScore()) {
                        if (TrJr.INSTANCE.getScrW() < 1080) {
                            TrJr.INSTANCE.font3.draw(batch, "B: " + settings.gethHighScore(), 48, height - 90);
                        } else {
                            TrJr.INSTANCE.font2.draw(batch, "B: " + settings.gethHighScore(), 80, height - 160);
                        }
                    } else {
                        if (TrJr.INSTANCE.getScrW() < 1080) {
                            TrJr.INSTANCE.fontCyan3.draw(batch, "New best!", 48, height - 90);
                        } else {
                            TrJr.INSTANCE.fontCyan2.draw(batch, "New best!", 80, height - 160);
                        }
                    }
                }
                if (settings.getDifficulty().equals("Cursed")) {
                    if (playTime < settings.getcHighScore()) {
                        if (TrJr.INSTANCE.getScrW() < 1080) {
                            TrJr.INSTANCE.font3.draw(batch, "B: " + settings.getcHighScore(), 48, height - 90);
                        } else {
                            TrJr.INSTANCE.font2.draw(batch, "B: " + settings.getcHighScore(), 80, height - 160);
                        }
                    } else {
                        if (TrJr.INSTANCE.getScrW() < 1080) {
                            TrJr.INSTANCE.fontCyan3.draw(batch, "New best!", 48, height - 90);
                        } else {
                            TrJr.INSTANCE.fontCyan2.draw(batch, "New best!", 80, height - 160);
                        }
                    }
                }

                if (playTime<=-1.5) TrJr.INSTANCE.fontBig.draw(batch, "3", width/2f-64, height / 1.5f);
                if (playTime>=-1.5 && playTime<-1) TrJr.INSTANCE.fontBig.draw(batch, "2", width/2f-64, height / 1.5f);
                if (playTime>=-1 && playTime<-0.5) TrJr.INSTANCE.fontBig.draw(batch, "1", width/2f-56, height / 1.5f);
                if (playTime>=-0.5 && playTime<0) TrJr.INSTANCE.fontCyanBig.draw(batch, "GO", width/2f-128, height / 1.5f);

                if (playTime <= 0) {
                    TrJr.INSTANCE.font2.draw(batch, "Control area", 20, height / 2f + 40);
                }

                if (settings.isFpsOn()) {
                    TrJr.INSTANCE.font2.draw(batch, "F: " + Gdx.graphics.getFramesPerSecond(), 80, height - 195);
                }

                if (settings.isSpeedOn()) {
                    TrJr.INSTANCE.font2.draw(batch, "V: " + velocityg, 80, height - 230);
                }
                if (player.oob && playTime > 0) {
                    TrJr.INSTANCE.font2.draw(batch, "Move down", 20, height / 2f + 40);
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
                    if (settings.getDifficulty().equals("Beginner")) {
                        if (playTime > this.ehighScoreg) {
                            settings.seteHighScore(strDouble);
                            EndScreen.newBest = true;
                        }
                    }
                    if (settings.getDifficulty().equals("Medium")) {
                        if (playTime > this.nhighScoreg) {
                            settings.setnHighScore(strDouble);
                            EndScreen.newBest = true;
                        }
                    }
                    if (settings.getDifficulty().equals("Expert")) {
                        if (playTime > this.hhighScoreg) {
                            settings.sethHighScore(strDouble);
                            EndScreen.newBest = true;
                        }
                    }
                    if (settings.getDifficulty().equals("Cursed")) {
                        if (playTime > this.chighScoreg) {
                            settings.setcHighScore(strDouble);
                            EndScreen.newBest = true;
                        }
                    }
                    TrJr.INSTANCE.setScreen(new EndScreen(camerag));
                }
            }
    }

}
