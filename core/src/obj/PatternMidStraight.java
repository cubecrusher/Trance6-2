package obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.cubecrusher.trancej.GameScreen;
import com.cubecrusher.trancej.TrJr;

public class PatternMidStraight {
    protected float x, y;
    protected int speed;
    public float velocity;
    protected GameScreen gameScreen;
    public Polygon polygon;
    protected ShapeRenderer shapeRenderer;
    protected float[] verticesM = {0,0,0,0,0,0,0,0};
    public boolean isOut = true;

    public PatternMidStraight(GameScreen gameScreen){
        this.x = 0;
        this.y = TrJr.INSTANCE.getScrH()+100;
        this.gameScreen = gameScreen;
        this.polygon = new Polygon(verticesM);
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

            verticesM[0] = 2 * TrJr.INSTANCE.getScrW() / 5f;
            verticesM[1] = y;

            verticesM[2] = 2 * TrJr.INSTANCE.getScrW() / 5f;
            verticesM[3] = y + TrJr.INSTANCE.getScrW();

            verticesM[4] = 2 * TrJr.INSTANCE.getScrW() / 5f;
            verticesM[5] = y + TrJr.INSTANCE.getScrW();

            verticesM[6] = 2 * TrJr.INSTANCE.getScrW() / 5f;
            verticesM[7] = y;

            polygon.setVertices(verticesM);
            polygon.getVertices();
    }

    public void render() {
        if (!gameScreen.hasCollided) {
            update();
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.polygon(verticesM);
            shapeRenderer.end();
        }
    }
}
