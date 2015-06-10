package com.geniusclone.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.geniusclone.game.GameplayClassic;

public class GameScreen implements Screen {

    private Game myGame;
    private GameplayClassic gamePlay;

    public GameScreen(Game g) {
        myGame = g;
        gamePlay = new GameplayClassic();
        Gdx.input.setInputProcessor(gamePlay);
    }

    @Override
    public void render(float delta) {
        gamePlay.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

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
        gamePlay.dispose();
    }
}
