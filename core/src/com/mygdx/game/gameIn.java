package com.mygdx.game;

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
import com.badlogic.gdx.utils.ScreenUtils;

public class gameIn implements Screen {
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
    TankStarsGame game;
    Texture backg;
    Texture backgg;
    Texture backline;
    Texture borderline;
    Texture mountain;
    Texture player1health;
    Texture player2health;
    Texture firelabel;
    Texture leftarrowmovemnt1;
    Texture rightarrowmovemnt1;
    Texture leftarrowmovemnt2;
    Texture rightarrowmovemnt2;
    Texture vslabel;
    Texture frosttank;
    Texture mark1tankface;
    Texture homebutton;
    Texture aimmer;
    Texture aimmer2;
    Texture fireattack;

    Texture pausebutton;
    public gameIn(TankStarsGame game) {
        this.game = game;
    }
    @Override
    public void show() {
        backg = new Texture("backg.jpg");
        backgg = new Texture("backgg.png");
        backline = new Texture("backline.jpg");
        borderline = new Texture("borderline.png");
        mountain = new Texture("mout.png");
        player1health = new Texture("player1health.png");
        player2health = new Texture("player2health.png");
        firelabel = new Texture("firelabel.png");
        leftarrowmovemnt1 = new Texture("leftarrowmovement.png");
        rightarrowmovemnt1 = new Texture("rightarrowmovement.png");
        leftarrowmovemnt2 = new Texture("leftarrowmovement.png");
        rightarrowmovemnt2 = new Texture("rightarrowmovement.png");
        vslabel = new Texture("vslabel.png");
        frosttank = new Texture("frosttank.png");
        mark1tankface = new Texture("mark1tankface.png");
        homebutton = new Texture("homebutton.png");
        aimmer = new Texture("aimmer.png");
        aimmer2 = new Texture("aimmer.png");
        fireattack = new Texture("fireattack.png");
        pausebutton = new Texture("pausebutton.png");
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
        bdef.position.set(-10, 10);
        //body definition2
        BodyDef bdef2 = new BodyDef();
        bdef2.type = BodyDef.BodyType.DynamicBody;
        bdef2.position.set(10, 10);

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
//        boxSprite = new Sprite(new Texture("frosttank.png"));
//        System.out.println("boxSprite: "+boxSprite);
//        System.out.println(boxSprite.getWidth());
//        System.out.println(boxSprite.getHeight());
//        boxSprite.setSize(250,250);
//        //boxSprite.setOrigin(boxSprite.getWidth()/2,boxSprite.getHeight()/2);
//        box.setUserData(boxSprite);


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
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        //game.batch.draw(backg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(backgg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(mountain, 0, 0, Gdx.graphics.getWidth(), 500);
        game.batch.draw(pausebutton,0,700,50,50);

//        game.batch.draw(backline, 0, 0, Gdx.graphics.getWidth(), 200);
//        game.batch.draw(borderline, -400, 150, Gdx.graphics.getWidth()+800, 100);

        game.batch.draw(player1health, 200, 775, 400, 90);
        game.batch.draw(player2health, 900, 775, 400, 100);
        game.batch.draw(firelabel, 600, 90, 150, 125);
        if (Gdx.input.getX() > 580 && Gdx.input.getX() < 720 && Gdx.input.getY() > 660 && Gdx.input.getY() < 780) {
            game.batch.draw(firelabel, 590, 80, 175, 150);
//            if (Gdx.input.isTouched()) {
//                game.setScreen(new gameIn(game));
//            }
        }
        game.batch.draw(fireattack, 750, 90, 150, 125);

        game.batch.draw(leftarrowmovemnt1, 150, 170, 100, 100);
        if (Gdx.input.getX() > 150 && Gdx.input.getX() < 250 && Gdx.input.getY() > 620 && Gdx.input.getY() < 720) {
            game.batch.draw(leftarrowmovemnt1, 140, 160, 125, 125);
        }
        game.batch.draw(rightarrowmovemnt1, 250, 170, 100, 100);
        if (Gdx.input.getX() > 250 && Gdx.input.getX() < 350 && Gdx.input.getY() > 620 && Gdx.input.getY() < 720) {
            game.batch.draw(rightarrowmovemnt1, 240, 160, 125, 125);
        }
        game.batch.draw(aimmer, 150, -5, 200, 200);


        game.batch.draw(leftarrowmovemnt2, 1150, 170, 100, 100);
        if (Gdx.input.getX() > 1150 && Gdx.input.getX() < 1250 && Gdx.input.getY() > 630 && Gdx.input.getY() < 730) {
            game.batch.draw(leftarrowmovemnt2, 1140, 160, 120, 120);
        }
        game.batch.draw(rightarrowmovemnt2, 1250, 170, 100, 100);
        if (Gdx.input.getX() > 1250 && Gdx.input.getX() < 1350 && Gdx.input.getY() > 630 && Gdx.input.getY() < 730) {
            game.batch.draw(rightarrowmovemnt2, 1240, 160, 120, 120);
        }
        game.batch.draw(aimmer2, 1150, -5, 200, 200);
        game.batch.draw(vslabel, 700, 750, 120, 120);
        //tilt the image to 45 degrees
        game.batch.draw(frosttank, 160, 270, 150, 200);
        game.batch.draw(mark1tankface, 1150, 302, 120, 80);
        game.batch.draw(homebutton, -20, 780, 130, 80);
        if (Gdx.input.getX() > 0 && Gdx.input.getX() < 130 && Gdx.input.getY() > 20 && Gdx.input.getY() < 100) {
            game.batch.draw(homebutton, -30, 770, 150, 100);
            if (Gdx.input.isTouched()) {
                game.setScreen(new choosetank(game));
            }
        }
//        game.batch.draw(pausebutton, 0, 700, 50, 50);
//        if (Gdx.input.getX() > 0 && Gdx.input.getX() < 50 && Gdx.input.getY() > 100 && Gdx.input.getY() < 150) {
//            game.batch.draw(pausebutton, -10, 690, 70, 70);
//            if (Gdx.input.isTouched()) {
//                game.setScreen(new choosetank(game));
//            }
//        }

        //Gdx.gl.glClearColor(0, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

        game.batch.end();
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

    }

    @Override
    public void dispose() {

    }
}
