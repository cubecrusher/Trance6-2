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
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private Settings settings;
    private EndScreen endScreen;

    protected Player player;
    protected Obstacle obstacle;
    protected Obstacle2 obstacle2;
    protected Obstacle3 obstacle3;
    protected Obstacle4 obstacle4;

    private final int width = Gdx.graphics.getWidth();
    private final int height = Gdx.graphics.getHeight();
    private final double rng = Math.random();

    protected float playTime = 0.00f;
    protected float highScore = 0.00f;
    public Float highScoreg;
    public Float strDouble;

    protected int bpm;
    protected int musicDur; //in seconds
    protected int musicPos;
    protected int musicN;

    public boolean hasCollided;
    protected int n, money, plays;


    public GameScreen(OrthographicCamera camera){
        this.camerag = camera;
        this.settings = new Settings();
        this.camerag.position.set(new Vector3(TrJr.INSTANCE.getScrW()/2f, TrJr.INSTANCE.getScrH()/2f,0));
        this.batch = new SpriteBatch();
        this.n = 1;
        this.player = new Player(this);
        this.hasCollided = false;
        this.obstacle = new Obstacle(this);
        this.obstacle2 = new Obstacle2(this);
        this.obstacle3 = new Obstacle3(this);
        this.obstacle4 = new Obstacle4(this);
        this.shapeRenderer = new ShapeRenderer();
        this.highScoreg = settings.getHighScore();
        this.money = settings.getMoney();
        this.plays = settings.getPlays();
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
                Assets.lightSpeed.play();
                bpm = 140;
                musicDur = 198;
                musicN = 3;
            }
            else if (rng<=0.6 && rng>0.5) {
                Assets.mcombat65.play();
                bpm = 140;
                musicDur = 198;
                musicN = 3;
            }
            else if (rng<=0.8 && rng>0.7) {
                Assets.mcombat7.play();
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
                Assets.mcombat2.play();
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
            this.obstacle.update();
            this.obstacle2.update();
            this.obstacle3.update();
            this.obstacle4.update();
            this.camerag.update();
            if (Intersector.intersectPolygons(player.polygon, obstacle.polygon, null) || Intersector.intersectPolygons(player.polygon, obstacle2.polygon, null) || Intersector.intersectPolygons(player.polygon, obstacle3.polygon, null) || Intersector.intersectPolygons(player.polygon, obstacle4.polygon, null))
                hasCollided = true;
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            Gdx.input.setCatchKey(Input.Keys.BACK,true);
            TrJr.INSTANCE.setScreen(new MainScreen(camerag));
            if (n<=2) {
                shapeRenderer.setAutoShapeType(true);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.WHITE);
                shapeRenderer.rect(0, 0, TrJr.INSTANCE.getScrW(), TrJr.INSTANCE.getScrH());
                shapeRenderer.end();
                n++;
            }
            Assets.stopAllGameMusic();
            TrJr.INSTANCE.setScreen(new MainScreen(camerag));
        }
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

                this.obstacle.velocity += 0.005;
                this.obstacle2.velocity += 0.005;
                this.obstacle3.velocity += 0.005;
                this.obstacle4.velocity += 0.005;

                String velocityg = String.format("%.0f", obstacle.velocity * Gdx.graphics.getFramesPerSecond());

                this.obstacle.render();
                this.obstacle2.render();
                this.obstacle3.render();
                this.obstacle4.render();
                this.player.render();

                if (playTime <= 0.075) {
                    shapeRenderer.setAutoShapeType(true);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.setColor(Color.WHITE);
                    shapeRenderer.rect(0, 0, TrJr.INSTANCE.getScrW(), TrJr.INSTANCE.getScrH());
                    shapeRenderer.end();
                }
                if (playTime <= 4.8) {
                    shapeRenderer.setAutoShapeType(true);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.setColor(Color.BLACK);
                    shapeRenderer.rect(2, TrJr.INSTANCE.getScrH() / 2f - 10 - 2, TrJr.INSTANCE.getScrW(), 10);
                    shapeRenderer.setColor(Color.WHITE);
                    shapeRenderer.rect(0, TrJr.INSTANCE.getScrH() / 2f - 10, TrJr.INSTANCE.getScrW(), 10);
                    shapeRenderer.end();
                } else {
                    if (player.oob) {
                        shapeRenderer.setAutoShapeType(true);
                        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                        shapeRenderer.setColor(Color.BLACK);
                        shapeRenderer.rect(2, TrJr.INSTANCE.getScrH() / 2f - 10 - 2, TrJr.INSTANCE.getScrW(), 10);
                        shapeRenderer.setColor(Color.WHITE);
                        shapeRenderer.rect(0, TrJr.INSTANCE.getScrH() / 2f - 10, TrJr.INSTANCE.getScrW(), 10);
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
                    Assets.gui2Small.draw(batch, "Control area", 22, TrJr.INSTANCE.getScrH() / 2f - 2 + 40);
                    Assets.guiSmall.draw(batch, "Control area", 20, TrJr.INSTANCE.getScrH() / 2f + 40);
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
                    Assets.gui2Small.draw(batch, "Move down", 22, TrJr.INSTANCE.getScrH() / 2f - 2 + 40);
                    Assets.guiSmall.draw(batch, "Move down", 20, TrJr.INSTANCE.getScrH() / 2f + 40);
                }
                batch.end();
            } else {
                this.player.renderOver();
                Assets.stopAllGameMusic();
                if (settings.isSoundOn()) Assets.playSound(Assets.gameOver);
                if (player.nn > 6000) {
                    settings.setPlays(plays);
                    EndScreen.time = strDouble;
                    if (playTime > this.highScoreg) {
                        settings.setHighScore(strDouble);
                        EndScreen.newBest = true;
                    }
                    TrJr.INSTANCE.setScreen(new EndScreen(camerag));
                }
            }
    }
}
