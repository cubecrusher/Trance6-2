package obj2;

import com.badlogic.gdx.physics.box2d.*;

public class Bodyobj {
    public static Body createBody(float x, float y, float width, float height, boolean isstatic, float density, World world, ContactType type){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isstatic == false ? BodyDef.BodyType.DynamicBody : BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x/Constant.what, y/Constant.what);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2/ Constant.what, height/2/Constant.what);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        body.createFixture(fixtureDef).setUserData(type);
        shape.dispose();
        return body;
    }
}
