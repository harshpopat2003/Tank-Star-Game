package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class startScreen implements Screen {

    Texture img;
    Texture startbutton;
    TankStarsGame game;
    Music beatbackgroud;

    public startScreen(TankStarsGame game) {
        this.game = game;
    }
    public startScreen() {}
    @Override
    public void show() {
        img = new Texture("startingloading.jpg");
        startbutton = new Texture("startbutton.png");
        beatbackgroud = Gdx.audio.newMusic(Gdx.files.internal("beat.mp3"));
        beatbackgroud.setLooping(true);
        beatbackgroud.play();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(startbutton, 625, -65, 250, 250);
        if (Gdx.input.getX() > 625 && Gdx.input.getX() < 875 && Gdx.input.getY() > 625 && Gdx.input.getY() < 875) {
            game.batch.draw(startbutton, 615, -75, 275, 275);
            if (Gdx.input.isTouched()) {
                game.setScreen(new choosetank(game));
                beatbackgroud.stop();
            }
        }
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
        game.batch.dispose();
        img.dispose();
        startbutton.dispose();
    }
}
