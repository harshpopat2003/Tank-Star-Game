package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class Gamedemo implements Screen {

    private World world;
    private Box2DDebugRenderer b2dr;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private final float TIMESTEP = 1/60f;
    private final int VELOCITYITERATIONS = 8;
    private final int POSITIONITERATIONS = 3;
    private float speed =50;
    private Vector2 movement =new Vector2();
    private Body box;
    private Body box2;
    private Sprite boxSprite;
    private final float PIXELS_TO_METERS = 32;
    private Array<Body> tmpBodies = new Array<Body>();


    public Gamedemo(TankStarsGame game) {
    }

    @Override
    public void show() {
    world = new World(new Vector2(0, -9.81f), true);
    b2dr = new Box2DDebugRenderer();
    batch = new SpriteBatch();
    camera = new OrthographicCamera(Gdx.graphics.getWidth()/50, Gdx.graphics.getHeight()/50);

    Gdx.input.setInputProcessor(new InputController(){
        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.UP:
                    movement.y=speed;
                    System.out.println("UP");
                    break;
                case Input.Keys.DOWN:
                    movement.y=-speed;
                    System.out.println("DOWN");
                    break;
                case Input.Keys.LEFT:
                    movement.x=-speed;
                    System.out.println("LEFT");
                    break;
                case Input.Keys.RIGHT:
                    movement.x=speed;
                    System.out.println("RIGHT");
                //case Input.Keys.ESCAPE:
                    //System.out.println("ESCAPE");
                    //TankStarsGame game = new TankStarsGame();
                    //((Game) Gdx.app.getApplicationListener()).setScreen(new gameIn(game));
            }
            return true;
        }
        @Override
        public boolean keyUp(int keycode){
            switch(keycode){
                case Input.Keys.UP:
                case Input.Keys.DOWN:
                    movement.y = 0;
                    break;
                case Input.Keys.LEFT:
                case Input.Keys.RIGHT:
                    movement.x = 0;
                    break;
            }
            return true;
        }
    });

    //body defination
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(5, 10);
        //body definition2
        BodyDef bdef2 = new BodyDef();
        bdef2.type = BodyDef.BodyType.DynamicBody;
        bdef2.position.set(0, 10);

        //square shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);

        //square shape2
        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(0.5f, 0.5f);


        //fixture defination
        FixtureDef fdef = new FixtureDef();
        fdef.shape= shape;
        fdef.density = 2.5f;
        fdef.friction = 0.25f;
        fdef.restitution = 0;

        //create body
        box=world.createBody(bdef);
        box.createFixture(fdef);
        boxSprite = new Sprite(new Texture("frosttank.png"));
        System.out.println("boxSprite: "+boxSprite);
        System.out.println(boxSprite.getWidth());
        System.out.println(boxSprite.getHeight());
        boxSprite.setSize(250,250);
        //boxSprite.setOrigin(boxSprite.getWidth()/2,boxSprite.getHeight()/2);
        box.setUserData(boxSprite);


        box2=world.createBody(bdef2);
        box2.createFixture(fdef);
        shape.dispose();
        shape2.dispose();

        //Ground
        bdef.type= BodyDef.BodyType.StaticBody;
        bdef.position.set(0, 0);

        //ground shape
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{
                new Vector2(-500, 0),
                new Vector2(500, 0)});// to get the ground add all vector points

        //fixture defination
        fdef.shape = groundShape;
        fdef.friction = 0.25f;
        fdef.restitution = 0;

        world.createBody(bdef).createFixture(fdef);
        groundShape.dispose();






    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world, camera.combined);
        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
        box.applyForceToCenter(movement, true);

        batch.begin();
        world.getBodies(tmpBodies);
        for (Body body : tmpBodies)
            if(body.getUserData() != null && body.getUserData() instanceof Sprite){
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x-sprite.getWidth()/2, body.getPosition().y-sprite.getHeight()/2);
                sprite.setRotation((float) Math.toDegrees(body.getAngle()));
                sprite.draw(batch);
            }
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        boxSprite.getTexture().dispose();

    }

}
