package obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.cubecrusher.trancej.GameScreen;
import com.cubecrusher.trancej.TrJr;

public class PatternDoubleSlide {
    protected float x, y;
    protected int speed;
    public float velocity;
    protected GameScreen gameScreen;
    public Polygon polygon;
    protected ShapeRenderer shapeRenderer;
    public boolean colordraw = false;
    protected float[] vertices = new float[8];
    protected boolean isOut = true;
    protected int pos;

    public PatternDoubleSlide(GameScreen gameScreen) {
        this.x = 0;
        this.y = TrJr.INSTANCE.getScrH() + 100;

        vertices[0] = x;
        vertices[1] = y;

        vertices[2] = x;
        vertices[3] = y + 100;

        vertices[4] = x + 100;
        vertices[5] = x + 100;

        vertices[6] = x + 100;
        vertices[7] = y;

        this.gameScreen = gameScreen;
        this.polygon = new Polygon(vertices);
        this.speed = 1;
        this.velocity = TrJr.INSTANCE.getScrH() / 120f;
        this.shapeRenderer = new ShapeRenderer();
    }

    public void update() {
        if (y < -200) {
            isOut = true;
        }
        y = y - speed * velocity;

        vertices[0] = x;
        vertices[1] = y;

        vertices[2] = x;
        vertices[3] = y + TrJr.INSTANCE.getScrW() / 5f;

        vertices[4] = x + TrJr.INSTANCE.getScrW() / 5f;
        vertices[5] = y + TrJr.INSTANCE.getScrW() / 5f;

        vertices[6] = x + TrJr.INSTANCE.getScrW() / 5f;
        vertices[7] = y;

        polygon.setVertices(vertices);
        polygon.getVertices();
    }

    public void render() {
        if (!gameScreen.hasCollided) {
            update();
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(x, y, TrJr.INSTANCE.getScrW() / 5f, y + TrJr.INSTANCE.getScrW() / 5f);
            //shapeRenderer.rect(x,y,TrJr.INSTANCE.getScrW()/5f, TrJr.INSTANCE.getScrW()/5f);
            shapeRenderer.end();
        }
    }
}