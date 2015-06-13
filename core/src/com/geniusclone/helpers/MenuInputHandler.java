package com.geniusclone.helpers;

import com.badlogic.gdx.InputProcessor;

public class MenuInputHandler implements InputProcessor {
    private SimpleButton playButton;
    private SimpleButton exitButton;
    private SimpleButton optionButton;

    public MenuInputHandler(SimpleButton lPlayButton, SimpleButton lExitButton, SimpleButton lOptionButton) {
        playButton = lPlayButton;
        exitButton = lExitButton;
        optionButton = lOptionButton;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        playButton.onButtonClick(screenX, screenY);
        exitButton.onButtonClick(screenX, screenY);
        optionButton.onButtonClick(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        playButton.onButtonRelease(screenX, screenY);
        exitButton.onButtonRelease(screenX, screenY);
        optionButton.onButtonRelease(screenX, screenY);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}