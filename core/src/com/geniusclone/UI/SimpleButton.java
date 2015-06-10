package com.geniusclone.UI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class SimpleButton {
    private Sprite buttonReleased, buttonPressed;
    boolean isPressed = false;
    long lastTimeDown = 0;

    public SimpleButton(TextureRegion textureReleased, TextureRegion texturePressed, float x, float y, float width, float height) {
        buttonReleased = new Sprite(textureReleased);
        buttonReleased.setPosition(x, y);
        buttonReleased.setSize(width, height);

        buttonPressed = new Sprite(texturePressed);
        buttonPressed.setPosition(x, y);
        buttonPressed.setSize(width, height);
    }

    public boolean isPressed() {
        return isPressed;
    }

    public boolean isJustUp() {
        return (lastTimeDown >= System.currentTimeMillis());
    }

    private boolean checkIfClicked(float ix, float iy) {
        if (ix > buttonReleased.getX() && ix < buttonReleased.getX() + buttonReleased.getWidth()) {
            if (iy > buttonReleased.getY() && iy < buttonReleased.getY() + buttonReleased.getHeight()) {
                return true;
            }
        }
        return false;
    }

    public Sprite getSkin() {
        return (isPressed) ? buttonPressed : buttonReleased;
    }

    public void onButtonClick(int x, int y) {
        OrthographicCamera camera;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
        Vector3 worldCoordinates = new Vector3(x, y, 0);
        camera.unproject(worldCoordinates);

        if(checkIfClicked(worldCoordinates.x, worldCoordinates.y)) {
            isPressed = true;
        }
    }

    public void onButtonRelease(int x, int y) {
        OrthographicCamera camera;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
        Vector3 worldCoordinates = new Vector3(x, y, 0);
        camera.unproject(worldCoordinates);

        if(isPressed && checkIfClicked(worldCoordinates.x, worldCoordinates.y)) {
            isPressed = false;
            lastTimeDown = System.currentTimeMillis() + 200;
        }
        else if(isPressed) {
            isPressed = false;
        }
    }

    public float getX() {
        return buttonReleased.getX();
    }

    public float getY() {
        return buttonReleased.getY();
    }
}
