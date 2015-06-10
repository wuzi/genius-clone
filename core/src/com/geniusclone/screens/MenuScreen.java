package com.geniusclone.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.TimeUtils;
import com.geniusclone.UI.SimpleButton;

public class MenuScreen implements Screen {
    private SimpleButton playButton, exitButton, optionButton;
    private Game myGame;
    private OrthographicCamera camera;
    private int axisY = 370;
    private boolean isAnimGoingUp = true;
    private long animUpdate;
    SpriteBatch batch;
    long soundId;

    public MenuScreen(Game g) {
        myGame = g;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);

        batch = new SpriteBatch();
        playButton = new SimpleButton(com.geniusclone.helpers.AssetLoader.playButtonReleased, com.geniusclone.helpers.AssetLoader.playButtonPressed, 90, 250, com.geniusclone.helpers.AssetLoader.playButtonPressed.getRegionWidth(), com.geniusclone.helpers.AssetLoader.playButtonPressed.getRegionHeight());
        optionButton = new SimpleButton(com.geniusclone.helpers.AssetLoader.optionButtonReleased, com.geniusclone.helpers.AssetLoader.optionButtonPressed, 90, 175, com.geniusclone.helpers.AssetLoader.optionButtonReleased.getRegionWidth(), com.geniusclone.helpers.AssetLoader.optionButtonReleased.getRegionHeight());
        exitButton = new SimpleButton(com.geniusclone.helpers.AssetLoader.exitButtonReleased, com.geniusclone.helpers.AssetLoader.exitButtonPressed, 90, 100, com.geniusclone.helpers.AssetLoader.exitButtonReleased.getRegionWidth(), com.geniusclone.helpers.AssetLoader.exitButtonReleased.getRegionHeight());
        Gdx.input.setInputProcessor(new com.geniusclone.helpers.MenuInputHandler(playButton, exitButton, optionButton));

        soundId = com.geniusclone.helpers.AssetLoader.soundTrack.loop();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(225 / 255f, 225 / 255f, 225 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(com.geniusclone.helpers.AssetLoader.menuBgTexture, 0, 0);
        batch.draw(com.geniusclone.helpers.AssetLoader.logo, 48, axisY);
        batch.draw(playButton.getSkin(), playButton.getX(), playButton.getY());
        batch.draw(optionButton.getSkin(), optionButton.getX(), optionButton.getY());
        batch.draw(exitButton.getSkin(), exitButton.getX(), exitButton.getY());
        batch.end();

        if (TimeUtils.millis() > animUpdate) {
            animUpdate = TimeUtils.millis() + 25;
            if(isAnimGoingUp) {
                axisY++;
                if(axisY > 380) isAnimGoingUp = false;
            }
            else if(!isAnimGoingUp) {
                axisY--;
                if(axisY < 360) isAnimGoingUp = true;
            }
        }

        if (exitButton.isJustUp()) {
            Gdx.app.exit();
        } else if (playButton.isJustUp()) {
            com.geniusclone.helpers.AssetLoader.soundTrack.stop(soundId);
            myGame.setScreen(new GameScreen(myGame));
        }
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("MenuScreen", "resizing2");
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
