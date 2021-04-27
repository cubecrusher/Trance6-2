package obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.cubecrusher.trancej.GameScreen;
import com.cubecrusher.trancej.TrJr;

public class PatternTriStraight {
    protected float x, y;
    protected int speed;
    public float velocity;
    protected GameScreen gameScreen;
    public Polygon polygonL, polygonR;
    protected ShapeRenderer shapeRenderer;
    protected float[] verticesL = {0,0,0,0,0,0,0,0};
    protected float[] verticesR = {0,0,0,0,0,0,0,0};
    public boolean isOut = true;

    public PatternTriStraight(GameScreen gameScreen){
        this.x = 0;
        this.y = TrJr.INSTANCE.getScrH()+100;
        this.gameScreen = gameScreen;
        this.polygonL = new Polygon(verticesL);
        this.polygonR = new Polygon(verticesR);
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

            verticesL[0] = TrJr.INSTANCE.getScrW() / 5f;
            verticesL[1] = y;

            verticesL[2] = TrJr.INSTANCE.getScrW() / 5f;
            verticesL[3] = y + TrJr.INSTANCE.getScrW();

            verticesL[4] = 2 * TrJr.INSTANCE.getScrW() / 5f;
            verticesL[5] = y + TrJr.INSTANCE.getScrW();

            verticesL[6] = 2 * TrJr.INSTANCE.getScrW() / 5f;
            verticesL[7] = y;


            verticesR[0] = 3 * TrJr.INSTANCE.getScrW() / 5f;
            verticesR[1] = y;

            verticesR[2] = 3 * TrJr.INSTANCE.getScrW() / 5f;
            verticesR[3] = y + TrJr.INSTANCE.getScrW();

            verticesR[4] = 4 * TrJr.INSTANCE.getScrW() / 5f;
            verticesR[5] = y + TrJr.INSTANCE.getScrW();

            verticesR[6] = 4 * TrJr.INSTANCE.getScrW() / 5f;
            verticesR[7] = y;


            polygonL.setVertices(verticesL);
            polygonL.getVertices();
            polygonR.setVertices(verticesR);
            polygonR.getVertices();
    }

    public void render() {
        if (!gameScreen.hasCollided) {
            update();
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.polygon(verticesL);
            shapeRenderer.polygon(verticesR);
            shapeRenderer.end();
        }
    }
}
