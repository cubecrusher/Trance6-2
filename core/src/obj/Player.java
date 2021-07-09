package obj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.cubecrusher.trancej.GameScreen;
import com.cubecrusher.trancej.Settings;
import com.cubecrusher.trancej.TrJr;

public class Player {
    public float x, y;
    public int width, height, speed;
    protected GameScreen gameScreen;
    public Polygon polygon;
    protected ShapeRenderer shapeRenderer;
    private Settings settings;
    public float lowerBound = TrJr.INSTANCE.getScrH() / 10f;
    protected float touchpt;
    protected float[] vertices = new float[6];
    public int nn = 0;
    public boolean white = true, oob = false;

    // Класс треугольника. Всё довольно просто.

    public Player(GameScreen gameScreen) {
        this.settings = new Settings();
        this.x = TrJr.INSTANCE.getScrW() / 2f;
        this.y = lowerBound;

        vertices[0] = x;
        vertices[1] = lowerBound;

        vertices[2] = x + 50;
        vertices[3] = lowerBound+88;

        vertices[4] = x+100;
        vertices[5] = lowerBound;

        this.gameScreen = gameScreen;
        this.polygon = new Polygon(vertices);
        this.speed = 100;
        this.width = TrJr.INSTANCE.getScrW()/12;
        this.height = 88;
        this.shapeRenderer = new ShapeRenderer();
    }

    public void update(){
        if (!gameScreen.hasCollided) {
            touchpt = Gdx.input.getY();
            if (x < 0) x = 0;
            if (x > TrJr.INSTANCE.getScrW() - width/2f) x = TrJr.INSTANCE.getScrW() - width/2f;
            if (touchpt >= TrJr.INSTANCE.getScrH() / 2f) {
                oob = false;
                Player.this.x = Gdx.input.getX() - width/2f;
                if (Player.this.x < 0) Player.this.x = 0;
                if (Player.this.x + width > TrJr.INSTANCE.getScrW()) Player.this.x = TrJr.INSTANCE.getScrW() - width;

                vertices[0] = x + 20;
                vertices[1] = lowerBound + 20;

                vertices[2] = x + width/2f;
                vertices[3] = (float) (lowerBound + Math.sqrt( (width/2f)*(width/2f)-(width/2f)*(width/2f)/4f )*2);

                vertices[4] = x + width - 20;
                vertices[5] = lowerBound + 20;

            } else {
                oob = true;
            }
            polygon.setVertices(vertices);
            polygon.getVertices();
            y = lowerBound;
        }
    }

    public Color toRGB(int r, int g, int b) {
        float RED = r / 255.0f;
        float GREEN = g / 255.0f;
        float BLUE = b / 255.0f;
        return new Color(RED, GREEN, BLUE, 1);
    }


    public void render(){
        if (!gameScreen.hasCollided) {
            update();
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            if (settings.getDifficulty().matches("Beginner")) shapeRenderer.setColor(Color.GREEN);
            if (settings.getDifficulty().matches("Medium")) shapeRenderer.setColor(toRGB(255,216,0));
            if (settings.getDifficulty().matches("Expert")) shapeRenderer.setColor(toRGB(128,0,0));
            if (settings.getDifficulty().matches("Cursed")) shapeRenderer.setColor(toRGB(178,0,255));
            shapeRenderer.triangle(x, lowerBound-10, x + width/2f, (float) (lowerBound + Math.sqrt( (width/2f)*(width/2f)-(width/2f)*(width/2f)/4f )*2) - 10, x + width, lowerBound - 10);
            if (settings.getColor()==0) shapeRenderer.setColor(Color.WHITE);
            if (settings.getColor()==1) shapeRenderer.setColor(Color.BLUE);
            if (settings.getColor()==2) shapeRenderer.setColor(Color.RED);
            if (settings.getColor()==3) shapeRenderer.setColor(Color.GREEN);
            if (settings.getColor()==4) shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.triangle(x, lowerBound, x + width/2f, (float) (lowerBound + Math.sqrt( (width/2f)*(width/2f)-(width/2f)*(width/2f)/4f )*2), x + width, lowerBound);
            shapeRenderer.end();
        }
    }
    public void renderOver(){
        if (white) {
            nn += 150;
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            if (settings.getColor()==0) shapeRenderer.setColor(Color.WHITE);
            if (settings.getColor()==1) shapeRenderer.setColor(Color.BLUE);
            if (settings.getColor()==2) shapeRenderer.setColor(Color.RED);
            if (settings.getColor()==3) shapeRenderer.setColor(Color.GREEN);
            if (settings.getColor()==4) shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.triangle(x - nn, lowerBound - nn, x + width/2f, (float) (lowerBound + Math.sqrt( (width/2f)*(width/2f)-(width/2f)*(width/2f)/4f )*2) + nn, x + width + nn, lowerBound - nn);
            shapeRenderer.end();
            if (nn>5000) {
                white = false;
                nn = 0;
            }
        }
        else renderBlack();

    }
    public void renderBlack(){
        nn += 150;
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.triangle(x - nn, lowerBound - nn, x + width/2f, (float) (lowerBound + Math.sqrt( (width/2f)*(width/2f)-(width/2f)*(width/2f)/4f )*2) + nn, x + width + nn, lowerBound - nn);
        shapeRenderer.end();
    }

}
