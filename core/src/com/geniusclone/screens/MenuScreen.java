package com.geniusclone.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.geniusclone.helpers.SimpleButton;
import com.geniusclone.helpers.MenuAssetLoader;

public class MenuScreen implements Screen {
    private Game myGame;
    private OrthographicCamera camera;
    private SimpleButton playButton, optionButton, exitButton;
    private int axisY = 370;
    private long animUpdate = 0;
    private boolean isAnimGoingUp = false;
    SpriteBatch batch;

    public MenuScreen(Game g) {
        myGame = g;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
        batch = new SpriteBatch();
        MenuAssetLoader.load();

        playButton = new SimpleButton(MenuAssetLoader.playButtonReleased, MenuAssetLoader.playButtonPressed, 90, 250, MenuAssetLoader.playButtonPressed.getRegionWidth(), MenuAssetLoader.playButtonPressed.getRegionHeight());
        optionButton = new SimpleButton(MenuAssetLoader.optionButtonReleased, MenuAssetLoader.optionButtonPressed, 90, 175, MenuAssetLoader.optionButtonReleased.getRegionWidth(), MenuAssetLoader.optionButtonReleased.getRegionHeight());
        exitButton = new SimpleButton(MenuAssetLoader.exitButtonReleased, MenuAssetLoader.exitButtonPressed, 90, 100, MenuAssetLoader.exitButtonReleased.getRegionWidth(), MenuAssetLoader.exitButtonReleased.getRegionHeight());
        Gdx.input.setInputProcessor(new com.geniusclone.helpers.MenuInputHandler(playButton, exitButton, optionButton));

        MenuAssetLoader.menuMusic.loop();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(225 / 255f, 225 / 255f, 225 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(MenuAssetLoader.backgroundTexture, 0, 0);
        batch.draw(MenuAssetLoader.logoTexture, 48, axisY);
        batch.draw(playButton.getSkin(), playButton.getX(), playButton.getY());
        batch.draw(optionButton.getSkin(), optionButton.getX(), optionButton.getY());
        batch.draw(exitButton.getSkin(), exitButton.getX(), exitButton.getY());
        batch.end();

        if (TimeUtils.millis() > animUpdate) {
            animUpdate = TimeUtils.millis() + 25;
            if (isAnimGoingUp) {
                axisY++;
                if (axisY > 380) isAnimGoingUp = false;
            } else if (!isAnimGoingUp) {
                axisY--;
                if (axisY < 360) isAnimGoingUp = true;
            }
        }

        if (exitButton.isJustUp()) {
            Gdx.app.exit();
        } else if (playButton.isJustUp()) {
            myGame.setScreen(new GameScreen(myGame));
            this.dispose();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        MenuAssetLoader.dispose();
    }

    @Override
    public void resize(int width, int height) {
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
}
