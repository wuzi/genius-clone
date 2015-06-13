package com.geniusclone.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.geniusclone.game.GameplayClassic;

public class GameScreen implements Screen {

    private Game myGame;
    private GameplayClassic gameplay;

    public GameScreen(Game g) {
        myGame = g;
        gameplay = new GameplayClassic();
        Gdx.input.setInputProcessor(gameplay);
    }

    @Override
    public void render(float delta) {
        gameplay.render();
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
        gameplay.dispose();
    }
}