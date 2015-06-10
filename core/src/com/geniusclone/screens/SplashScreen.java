package com.geniusclone.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class SplashScreen implements Screen {
    private SpriteBatch batch;
    private Game myGame;
    private Texture texture;
    private OrthographicCamera camera;
    private long startTime;
    private long rendCount;
    private float opacity = 0.0f;

    public SplashScreen(Game g)
    {
        myGame = g;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
        batch = new SpriteBatch();
        com.geniusclone.helpers.AssetLoader.muspellGames.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setColor(1.0f, 1.0f, 1.0f, opacity);
        batch.draw(texture, 0, 0);
        batch.end();
        rendCount++;

        if (TimeUtils.millis() > rendCount && opacity < 1.0f) {
            rendCount = TimeUtils.millis() + 25;
            opacity += 0.010f;
            if (opacity > 1.0f) opacity = 1.0f;
        }

        else if (TimeUtils.millis() > (startTime + 5000)) myGame.setScreen(new MenuScreen(myGame));

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        texture = new Texture(Gdx.files.internal("splash.jpg"));
        startTime = TimeUtils.millis();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();
    }
}
