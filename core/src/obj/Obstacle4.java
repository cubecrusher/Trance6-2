package obj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.cubecrusher.trancej.GameScreen;
import com.cubecrusher.trancej.TrJr;

public class Obstacle4 {
    protected float x, y;
    protected int speed;
    public float velocity;
    protected GameScreen gameScreen;
    public Polygon polygon;
    protected ShapeRenderer shapeRenderer;
    public boolean colordraw = false;
    protected boolean isOut = true;
    protected float[] vertices = new float[8];
    protected int pos;

    public Obstacle4(GameScreen gameScreen){
        this.x = 0;
        this.y = TrJr.INSTANCE.getScrH()+100;

        vertices[0] = x;
        vertices[1] = y;

        vertices[2] = x;
        vertices[3] = y+100;

        vertices[4] = x+100;
        vertices[5] = x+100;

        vertices[6] = x+100;
        vertices[7] = y;

        this.gameScreen = gameScreen;
        this.polygon = new Polygon(vertices);
        this.speed = 1;
        this.velocity = 10;
        this.shapeRenderer = new ShapeRenderer();
    }

    public int getRandom(){
        int position = (int) (Math.random() * 10);
        while (position >4) position = (int) (Math.random() * 10);
        return position;
    }

    public void update(){
        if (isOut) {
            this.pos = getRandom();
            if (pos == 0) Obstacle4.this.x = 0;
            if (pos == 1) Obstacle4.this.x = TrJr.INSTANCE.getScrW() / 5f;
            if (pos == 2) Obstacle4.this.x = 2 * TrJr.INSTANCE.getScrW() / 5f;
            if (pos == 3) Obstacle4.this.x = 3 * TrJr.INSTANCE.getScrW() / 5f;
            if (pos == 4) Obstacle4.this.x = 4 * TrJr.INSTANCE.getScrW() / 5f;
            isOut = false;
        }
        if (y<-200) {
            isOut = true;
            this.pos = getRandom();
            y = TrJr.INSTANCE.getScrH()+100;
        }
        y = y - speed * velocity;
        vertices[0] = x;
        vertices[1] = y;

        vertices[2] = x;
        vertices[3] = y+TrJr.INSTANCE.getScrW()/5f;

        vertices[4] = x+TrJr.INSTANCE.getScrW()/5f;
        vertices[5] = y+TrJr.INSTANCE.getScrW()/5f;

        vertices[6] = x+TrJr.INSTANCE.getScrW()/5f;
        vertices[7] = y;

        polygon.setVertices(vertices);
        polygon.getVertices();
    }

    public void render() {
        update();
        if (!gameScreen.hasCollided) {
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            if (colordraw) {
                shapeRenderer.setColor(Color.CYAN);
                shapeRenderer.rect(x + 50, y, TrJr.INSTANCE.getScrW() / 5f, TrJr.INSTANCE.getScrW() / 5f);
                shapeRenderer.setColor(Color.PINK);
                shapeRenderer.rect(x - 50, y, TrJr.INSTANCE.getScrW() / 5f, TrJr.INSTANCE.getScrW() / 5f);
            }
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(x, y, TrJr.INSTANCE.getScrW() / 5f, y + TrJr.INSTANCE.getScrW() / 5f);
            //shapeRenderer.rect(x,y,TrJr.INSTANCE.getScrW()/5f, TrJr.INSTANCE.getScrW()/5f);
            shapeRenderer.end();
        } else {
            shapeRenderer.setAutoShapeType(true);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(x, y, TrJr.INSTANCE.getScrW() / 5f, y + TrJr.INSTANCE.getScrW() / 5f);
            shapeRenderer.end();
        }
    }

}