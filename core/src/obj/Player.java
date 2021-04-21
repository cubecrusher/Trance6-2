package obj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.cubecrusher.trancej.GameScreen;
import com.cubecrusher.trancej.TrJr;

public class Player {
    public float x, y;
    public int width, height, speed;
    protected GameScreen gameScreen;
    public Polygon polygon;
    protected ShapeRenderer shapeRenderer;
    public float lowerBound = TrJr.INSTANCE.getScrH()/10f;
    protected float touchpt;
    protected float[] vertices = new float[6];
    public int nn = 0;
    public boolean white = true, oob = false;

    public Player(GameScreen gameScreen){
        this.x = TrJr.INSTANCE.getScrW()/2f;
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

    public void render(){
        if (!gameScreen.hasCollided) {
            update();
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.triangle(x+5, lowerBound-5, x + width/2f + 5, (float) (lowerBound + Math.sqrt( (width/2f)*(width/2f)-(width/2f)*(width/2f)/4f )*2) - 5, x + width + 5, lowerBound - 5);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.triangle(x, lowerBound, x + width/2f, (float) (lowerBound + Math.sqrt( (width/2f)*(width/2f)-(width/2f)*(width/2f)/4f )*2), x + width, lowerBound);
            shapeRenderer.end();
        }
    }
    public void renderOver(){
        if (white) {
            nn += 150;
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);
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
