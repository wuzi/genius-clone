package com.geniusclone.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.geniusclone.helpers.GameAssetLoader;
import com.geniusclone.screens.MenuScreen;

import java.util.ArrayList;
import java.util.Random;

public class GameplayClassic implements InputProcessor {
    private Game myGame;
    private boolean isPlayerTurn = false; // Check if it is player's turn
    private boolean isPlayingSquare = false; // Check if a square is playing
    private int squarePlayingID = 0; // ID of the square that is playing
    private long startPlayingSquare = 0; // Time square started to play + delay to next square to play

    private int currentLevel = 1; // Current player's level
    private int currentPlay = 0; // Current square player is playing in array
    private int mistakesRemaining = 3; // Number of mistakes the player may make

    private boolean isRestartButtonDown = false;
    private boolean isBackButtonDown = false;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private ArrayList<Integer> arrayColors = new ArrayList<Integer>();
    private Sprite redSquare, greenSquare, yellowSquare, blueSquare, circle;
    private Sprite redSquareGlow, greenSquareGlow, yellowSquareGlow, blueSquareGlow;

    private enum GameState {READY, RUNNING, GAMEOVER}

    private GameState currentState;

    public GameplayClassic(Game g) {
        myGame = g;
        GameAssetLoader.load();

        currentState = GameState.READY;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
        batch = new SpriteBatch();

        redSquare = new Sprite(GameAssetLoader.redSquare);
        redSquare.setPosition(0, 160);
        redSquareGlow = new Sprite(GameAssetLoader.redSquareGlow);
        redSquareGlow.setPosition(0, 160);

        yellowSquare = new Sprite(GameAssetLoader.yellowSquare);
        yellowSquare.setPosition(0, 320);
        yellowSquareGlow = new Sprite(GameAssetLoader.yellowSquareGlow);
        yellowSquareGlow.setPosition(0, 320);

        greenSquare = new Sprite(GameAssetLoader.greenSquare);
        greenSquare.setPosition(160, 160);
        greenSquareGlow = new Sprite(GameAssetLoader.greenSquareGlow);
        greenSquareGlow.setPosition(160, 160);

        blueSquare = new Sprite(GameAssetLoader.blueSquare);
        blueSquare.setPosition(160, 320);
        blueSquareGlow = new Sprite(GameAssetLoader.blueSquareGlow);
        blueSquareGlow.setPosition(160, 320);

        circle = new Sprite(GameAssetLoader.circleTexture);
        circle.setPosition(118, 247);


    }

    public void render() {
        switch (currentState) {
            case READY: {
                Gdx.gl.glClearColor(225 / 255f, 225 / 255f, 225 / 255f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.setProjectionMatrix(camera.combined);
                batch.begin();
                batch.draw(GameAssetLoader.backgroundTexture, 0, 0);
                batch.draw(GameAssetLoader.redSquare, redSquare.getX(), redSquare.getY());
                batch.draw(GameAssetLoader.yellowSquare, yellowSquare.getX(), yellowSquare.getY());
                batch.draw(GameAssetLoader.blueSquare, blueSquare.getX(), blueSquare.getY());
                batch.draw(GameAssetLoader.greenSquare, greenSquare.getX(), greenSquare.getY());
                batch.draw(GameAssetLoader.circleTexture, 120, 265);
                GameAssetLoader.font.draw(batch, "START", 138, 325);
                GameAssetLoader.font.draw(batch, "Touch start to begin", 80, 50);
                batch.end();
                break;
            }
            case RUNNING: {
                Gdx.gl.glClearColor(225 / 255f, 225 / 255f, 225 / 255f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.setProjectionMatrix(camera.combined);
                batch.begin();
                batch.draw(GameAssetLoader.backgroundTexture, 0, 0);
                batch.draw(GameAssetLoader.redSquare, redSquare.getX(), redSquare.getY());
                batch.draw(GameAssetLoader.yellowSquare, yellowSquare.getX(), yellowSquare.getY());
                batch.draw(GameAssetLoader.blueSquare, blueSquare.getX(), blueSquare.getY());
                batch.draw(GameAssetLoader.greenSquare, greenSquare.getX(), greenSquare.getY());
                GameAssetLoader.font.draw(batch, levelToString(), 20, 140);
                GameAssetLoader.font.draw(batch, "Mistakes remaining: " + mistakesRemaining, 20, 120);

                // If a square is playing and it is still playing
                // startPlayingSquare is the time it will play
                // draw the square glowing on the screen
                if (isPlayingSquare && startPlayingSquare > System.currentTimeMillis()) {
                    if (squarePlayingID == 0) {
                        batch.draw(GameAssetLoader.redSquareGlow, redSquareGlow.getX(), redSquareGlow.getY());
                    } else if (squarePlayingID == 1) {
                        batch.draw(GameAssetLoader.yellowSquareGlow, yellowSquareGlow.getX(), yellowSquareGlow.getY());
                    } else if (squarePlayingID == 2) {
                        batch.draw(GameAssetLoader.blueSquareGlow, blueSquareGlow.getX(), blueSquareGlow.getY());
                    } else if (squarePlayingID == 3) {
                        batch.draw(GameAssetLoader.greenSquareGlow, greenSquareGlow.getX(), greenSquareGlow.getY());
                    }
                }

                batch.end();

                // If it is not player turn, the computer will play the squares
                if (!isPlayerTurn) {
                    if (currentPlay < currentLevel && startPlayingSquare < System.currentTimeMillis()) {
                        if (currentPlay < arrayColors.size()) {
                            playSquare(arrayColors.get(currentPlay));
                            currentPlay++;
                        } else {
                            Random rand = new Random();
                            int color = rand.nextInt(4);
                            arrayColors.add(color);
                            playSquare(color);
                            currentPlay++;
                        }
                    }
                    // if the computer played all the squares, it will give the turn to the player and stop playing squares
                    else if (currentPlay == currentLevel && isPlayingSquare && startPlayingSquare < System.currentTimeMillis()) {
                        isPlayingSquare = false;
                        isPlayerTurn = true;
                        currentPlay = 0;
                    }
                }
                break;
            }
            case GAMEOVER: {
                Gdx.gl.glClearColor(225 / 255f, 225 / 255f, 225 / 255f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.setProjectionMatrix(camera.combined);
                batch.begin();
                batch.draw(GameAssetLoader.backgroundTexture, 0, 0);
                batch.draw(GameAssetLoader.redSquare, redSquare.getX(), redSquare.getY());
                batch.draw(GameAssetLoader.yellowSquare, yellowSquare.getX(), yellowSquare.getY());
                batch.draw(GameAssetLoader.blueSquare, blueSquare.getX(), blueSquare.getY());
                batch.draw(GameAssetLoader.greenSquare, greenSquare.getX(), greenSquare.getY());
                GameAssetLoader.font.draw(batch, levelToString(), 20, 140);
                GameAssetLoader.font.draw(batch, "Mistakes remaining: " + mistakesRemaining, 20, 120);
                batch.draw(GameAssetLoader.gameoverTexture, 0, 0);
                batch.draw((isRestartButtonDown) ? GameAssetLoader.restartButtonPressed : GameAssetLoader.restartButtonReleased, 170, 183);
                batch.draw((isBackButtonDown) ? GameAssetLoader.backButtonPressed : GameAssetLoader.backButtonReleased, 36, 183);
                batch.end();
                break;
            }
        }
    }

    // Play a square 0 = red, 1 = yellow, 2 = blue, 3 = green
    private void playSquare(int color) {
        switch (color) {
            case 0: { // Red
                isPlayingSquare = true;// We are going to play a square, so true
                squarePlayingID = 0;// ID of the square, red is 0
                // if it is the computer that is going to play 1000(1sec) delay is good, if it is the player we don't need delay (50ms for safe)
                startPlayingSquare = (!isPlayerTurn) ? System.currentTimeMillis() + (1000 - (currentLevel * 25)) : System.currentTimeMillis() + 50;
                // playing the sound
                GameAssetLoader.redsound.play();
                break;
            }
            case 1: { // Yellow
                isPlayingSquare = true;
                squarePlayingID = 1;
                startPlayingSquare = (!isPlayerTurn) ? System.currentTimeMillis() + (1000 - (currentLevel * 25)) : System.currentTimeMillis() + 50;
                GameAssetLoader.yellowsound.play();
                break;
            }
            case 2: { // Blue
                isPlayingSquare = true;
                squarePlayingID = 2;
                startPlayingSquare = (!isPlayerTurn) ? System.currentTimeMillis() + (1000 - (currentLevel * 25)) : System.currentTimeMillis() + 50;
                GameAssetLoader.bluesound.play();
                break;
            }
            case 3: { // Green
                isPlayingSquare = true;
                squarePlayingID = 3;
                startPlayingSquare = (!isPlayerTurn) ? System.currentTimeMillis() + (1000 - (currentLevel * 25)) : System.currentTimeMillis() + 50;
                GameAssetLoader.greensound.play();
                break;
            }
        }
    }

    private String levelToString() {
        return "Level: " + currentLevel;
    }

    public void dispose() {
        batch.dispose();
        GameAssetLoader.dispose();
    }

    private boolean checkTouchRegion(float ix, float iy, int x, int y, int w, int h) {
        Vector3 worldCoordinates = new Vector3(ix, iy, 0);
        camera.unproject(worldCoordinates);
        if (worldCoordinates.x > x && worldCoordinates.x < x + w) {
            if (worldCoordinates.y > y && worldCoordinates.y < y + h) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        switch (currentState) {
            case GAMEOVER: {
                if (checkTouchRegion(screenX, screenY, 170, 183, 120, 131)) {
                    isRestartButtonDown = true;
                } else if (checkTouchRegion(screenX, screenY, 36, 183, 120, 131)) {
                    isBackButtonDown = true;
                }
                break;
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        switch (currentState) {
            case READY: {
                if (checkTouchRegion(screenX, screenY, 120, 265, 87, 103)) {
                    currentState = GameState.RUNNING;
                }
                break;
            }
            case RUNNING: {
                if (isPlayerTurn) {
                    if (currentPlay < currentLevel && startPlayingSquare < System.currentTimeMillis()) {
                        startPlayingSquare = System.currentTimeMillis() + 50;
                        if (checkTouchRegion(screenX, screenY, (int) redSquare.getX(), (int) redSquare.getY(), (int) redSquare.getWidth(), (int) redSquare.getHeight())) { // Red
                            if (arrayColors.get(currentPlay) == 0) {
                                playSquare(0);
                                currentPlay++;
                                if (currentPlay == currentLevel) {
                                    currentPlay = 0;
                                    currentLevel++;
                                    isPlayerTurn = false;
                                    startPlayingSquare = System.currentTimeMillis() + 1000;
                                }
                            } else {
                                GameAssetLoader.errorSound.play();
                                mistakesRemaining--;
                                if (mistakesRemaining < 1) {
                                    currentState = GameState.GAMEOVER;
                                    GameAssetLoader.gameoverSound.play();
                                }
                            }
                        } else if (checkTouchRegion(screenX, screenY, (int) yellowSquare.getX(), (int) yellowSquare.getY(), (int) yellowSquare.getWidth(), (int) yellowSquare.getHeight())) { // Yellow
                            if (arrayColors.get(currentPlay) == 1) {
                                playSquare(1);
                                currentPlay++;
                                if (currentPlay == currentLevel) {
                                    currentPlay = 0;
                                    currentLevel++;
                                    isPlayerTurn = false;
                                    startPlayingSquare = System.currentTimeMillis() + 1000;
                                }
                            } else {
                                GameAssetLoader.errorSound.play();
                                mistakesRemaining--;
                                if (mistakesRemaining < 1) {
                                    currentState = GameState.GAMEOVER;
                                    GameAssetLoader.gameoverSound.play();
                                }
                            }
                        } else if (checkTouchRegion(screenX, screenY, (int) blueSquare.getX(), (int) blueSquare.getY(), (int) blueSquare.getWidth(), (int) blueSquare.getHeight())) { // Blue
                            if (arrayColors.get(currentPlay) == 2) {
                                playSquare(2);
                                currentPlay++;
                                if (currentPlay == currentLevel) {
                                    currentPlay = 0;
                                    currentLevel++;
                                    isPlayerTurn = false;
                                    startPlayingSquare = System.currentTimeMillis() + 1000;
                                }
                            } else {
                                GameAssetLoader.errorSound.play();
                                mistakesRemaining--;
                                if (mistakesRemaining < 1) {
                                    currentState = GameState.GAMEOVER;
                                    GameAssetLoader.gameoverSound.play();
                                }
                            }
                        } else if (checkTouchRegion(screenX, screenY, (int) greenSquare.getX(), (int) greenSquare.getY(), (int) greenSquare.getWidth(), (int) greenSquare.getHeight())) { // Green
                            if (arrayColors.get(currentPlay) == 3) {
                                playSquare(3);
                                currentPlay++;
                                if (currentPlay == currentLevel) {
                                    currentPlay = 0;
                                    currentLevel++;
                                    isPlayerTurn = false;
                                    startPlayingSquare = System.currentTimeMillis() + 1000;
                                }
                            } else {
                                GameAssetLoader.errorSound.play();
                                mistakesRemaining--;
                                if (mistakesRemaining < 1) {
                                    currentState = GameState.GAMEOVER;
                                    GameAssetLoader.gameoverSound.play();
                                }
                            }
                        }
                    }
                }
                break;
            }
            case GAMEOVER: {
                if (checkTouchRegion(screenX, screenY, 170, 183, 120, 131) && isRestartButtonDown) {
                    isPlayerTurn = false;
                    isPlayingSquare = false;
                    squarePlayingID = 0;
                    startPlayingSquare = 0;
                    currentLevel = 1;
                    currentPlay = 0;
                    mistakesRemaining = 3;
                    isRestartButtonDown = false;
                    isBackButtonDown = false;
                    currentState = GameState.RUNNING;
                } else if (checkTouchRegion(screenX, screenY, 36, 183, 120, 131)) {
                    myGame.setScreen(new MenuScreen(myGame));
                    this.dispose();
                }

                isRestartButtonDown = false;
                isBackButtonDown = false;
            }
            break;
        }
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