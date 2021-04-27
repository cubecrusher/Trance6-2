package obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.cubecrusher.trancej.GameScreen;
import com.cubecrusher.trancej.TrJr;

public class PatternMiddle {
    protected float x, y;
    protected int speed;
    public float velocity;
    protected GameScreen gameScreen;
    public Polygon polygon;
    protected ShapeRenderer shapeRenderer;
    protected float[] vertices = {0,0,0,0,0,0,0,0};
    public boolean isOut = true;

    public PatternMiddle(GameScreen gameScreen){
        this.x = 0;
        this.y = TrJr.INSTANCE.getScrH()+100;
        this.gameScreen = gameScreen;
        this.polygon = new Polygon(vertices);
        this.speed = 1;
        this.velocity = 1;
        this.shapeRenderer = new ShapeRenderer();
    }

    public void resetPos(){
        y=TrJr.INSTANCE.getScrH()+200;
    }

    public void update(){
        if (y<-200-TrJr.INSTANCE.getScrW()) isOut = true;
            y = y - speed * velocity;

            vertices[0] = TrJr.INSTANCE.getScrW() / 5f;
            vertices[1] = y;

            vertices[2] = TrJr.INSTANCE.getScrW() / 5f;
            vertices[3] = y + TrJr.INSTANCE.getScrW();

            vertices[4] = 3 * TrJr.INSTANCE.getScrW() / 5f;
            vertices[5] = y + TrJr.INSTANCE.getScrW();

            vertices[6] = 3 * TrJr.INSTANCE.getScrW() / 5f;
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
            shapeRenderer.rect(vertices[0], y, 3*TrJr.INSTANCE.getScrW()/5f, TrJr.INSTANCE.getScrW());
            shapeRenderer.end();
        }
    }
}
